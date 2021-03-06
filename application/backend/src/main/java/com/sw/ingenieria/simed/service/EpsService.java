package com.sw.ingenieria.simed.service;


import com.sw.ingenieria.simed.dto.EpsInputDTO;
import com.sw.ingenieria.simed.dto.LugarAtencionOutputDTO;
import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.EpsRepository;
import com.sw.ingenieria.simed.repository.LugarAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EpsService implements ServiceInterface  <Eps, Short>{
    @Autowired
    private EpsRepository epsRepository;

    @Autowired
    private LugarAtencionRepository lugarAtencionRepository;

    @Autowired
    private LugarAtencionService lugarAtencionService;

    @Override
    public Eps findById(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La EPS con id " + key + " no existe.");
        }
        return epsRepository.findById(key).get();
    }

    @Override
    public List<Eps> findAll() throws Exception {
        return (List <Eps>) epsRepository.findAll();
    }
    public List <Map<String, String>> findAllByEps() throws Exception {
        return epsRepository.findAllByEps();
    }

    @Override
    public Eps create(Eps entity) throws Exception {
        entity.setEstadoEps(true);
        return epsRepository.save(entity);
    }

    @Override
    public Eps update(Eps entity) throws Exception {
        Eps eps = epsRepository.findById(entity.getIdEps()).get();

        if (entity.getNombreEps() != null) {
            eps.setNombreEps(entity.getNombreEps());
        }
        if (entity.getDireccionEps() != null) {
        eps.setDireccionEps(entity.getDireccionEps());
        }
        if (entity.getTelefonoEps() != null) {
        eps.setTelefonoEps(entity.getTelefonoEps());
        }
        if (entity.getEstadoEps() != null) {
        eps.setEstadoEps(true);
        }
        if (entity.getLugarAtencionCollection() != null) {
           asignarLugar(entity);
        }
        return epsRepository.save(eps);
    }

    @Override
    public void delete(Short key) throws Exception {
        
    }

    public Eps deleteEPS(Short key) throws Exception {
        Eps eps = epsRepository.findById(key).get();
        eps.setEstadoEps(false);
        return epsRepository.save(eps);
    }

    public Boolean existeById (Short key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (epsRepository.findById(key) == null) {
            return false;
        }
        return epsRepository.existsById(key);
    }

    public Eps findId(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La EPS con id " + key + " no existe.");
        }
        return epsRepository.findById(key).get();
    }

    /**
     * Metodo para activar Eps
     * @param key id de Eps
     * @return
     * @throws Exception
     */
    @Deprecated
    public Eps activar(Short key) throws Exception {
        Eps eps = epsRepository.findById(key).get();
        eps.setEstadoEps(true);
        return epsRepository.save(eps);
    }

    /**
     * @param entity Objeto EpsInputDTO
     * @return Objeto EPS
     * @autor Johan Miguel Céspedes - Método que permite asignar uno o muchos Lugares de Atencion a una EPS.
     */
    public Eps asignarLugar(Eps entity) throws Exception {
        if (entity.getIdEps() == null || !existeById(entity.getIdEps())) {
            throw new ResourceNotFoundException("La EPS con Id " + entity.getIdEps() + " no existe.");
        }
        Eps eps = epsRepository.findByIdEpsAndEstadoEpsTrue(entity.getIdEps());
        /*if (eps.getLugarAtencionCollection() == null || eps.getLugarAtencionCollection().size() <= 0) {
            throw new ResourceNotFoundException("El Cliente no tiene lugares de atención asignados.");
        }*/
        if (entity.getLugarAtencionCollection() == null || entity.getLugarAtencionCollection().size() <= 0) {
            throw new ResourceNotFoundException("No se han seleccionado Lugare(s) de atención para asignar a la Eps.");
        }
        for (LugarAtencion lugarAt : entity.getLugarAtencionCollection()) {
            if (!lugarAtencionService.existeById(lugarAt.getIdLugaresAtencion())) {
                throw new ResourceNotFoundException("El Lugar de atención con id " + lugarAt.getIdLugaresAtencion() + " no existe.");
            }

        }
        eps.setLugarAtencionCollection(entity.getLugarAtencionCollection());
        return epsRepository.save(eps);
/*

        Collection lugarAtCollection = new LinkedList();
        if(eps.getLugarAtencionCollection().size()>0){
            lugarAtCollection.add(eps.getLugarAtencionCollection());

            for (LugarAtencion lugarAtencion: eps.getLugarAtencionCollection()) {
                lugarAtCollection.add(LugarAtencionOutputDTO.getDTO(lugarAtencion));

                for (LugarAtencion eps1: entity.getLugarAtencionCollection()) {
                    LugarAtencion lugarAtencionx = lugarAtencionRepository.findById(eps1.getIdLugaresAtencion()).get();

                    if(lugarAtencion.getIdLugaresAtencion() != eps1.getIdLugaresAtencion()){
                        eps.getLugarAtencionCollection().add(lugarAtencionx);
                        System.out.println("lugarAtencion en el servicio linea 132 = "
                                + lugarAtencion.getIdLugaresAtencion()  +"=" + eps1.getIdLugaresAtencion() );
                    }
                }

            }
        }
        eps.setLugarAtencionCollection(lugarAtCollection);
        //eps.setLugarAtencionCollection(entity.getLugarAtencionCollection());
        return epsRepository.save(eps);*/
    }
}
