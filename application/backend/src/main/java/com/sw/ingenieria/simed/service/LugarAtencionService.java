package com.sw.ingenieria.simed.service;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.repository.EpsRepository;
import com.sw.ingenieria.simed.repository.LugarAtencionRepository;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarAtencionService implements ServiceInterface <LugarAtencion,Long> {
    private final LugarAtencionRepository lugarAtencionRepository;
    private final EpsRepository epsRepository;


    public LugarAtencionService(LugarAtencionRepository lugarAtencionRepository, EpsRepository epsRepository){
        this.lugarAtencionRepository = lugarAtencionRepository;
        this.epsRepository = epsRepository;
    }

    @Override
    public LugarAtencion findById(Long key) throws Exception {
        System.out.println("este es el id que llega al servicio = " + key);
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El lugar de atención con ID " + key + " no existe.");
        }
        return lugarAtencionRepository.findById(key).get();

    }

    @Override
    public List<LugarAtencion> findAll() throws Exception {
        return (List<LugarAtencion>) lugarAtencionRepository.findAll();
    }

    @Override
    public LugarAtencion create(LugarAtencion entity) throws Exception {
        //entity.setFecha_creacion(LocalDate.now());
        return  lugarAtencionRepository.save(entity);
    }

    @Override
    public LugarAtencion update(LugarAtencion entity) throws Exception {
        LugarAtencion lugarAtencion = lugarAtencionRepository.findById(entity.getIdLugaresAtencion()).get();
        return lugarAtencionRepository.save(entity);
    }

    @Override
    public void delete(Long key) throws Exception {
        LugarAtencion lugarAtencion = lugarAtencionRepository.findById(key).get();
        lugarAtencion.setEstadoLugarAtencion(false);
        lugarAtencionRepository.save(lugarAtencion);
        //usuarioRepository.delete(usuario);

    }

    public Boolean existeById (Long key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacío.");
        }
        if (lugarAtencionRepository.findById(key) == null) {
            return false;
        }
        return lugarAtencionRepository.existsById(key);
    }

    public LugarAtencion findId(Long key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El lugar de atención con id " + key + " no existe.");
        }
        return lugarAtencionRepository.findById(key).get();
    }

    /**
     * Metodo para activar usuarios
     * @param key id del usuario
     * @return
     * @throws Exception
     */
    public LugarAtencion activar(Long key) throws Exception {
        LugarAtencion lugarAtencion = lugarAtencionRepository.findById(key).get();
        lugarAtencion.setEstadoLugarAtencion(true);
        return lugarAtencionRepository.save(lugarAtencion);
    }

    public List<LugarAtencion> obtenerLugarAtencionPorEps (Short idEps) {
        if (idEps == null) {
            throw new ResourceNotFoundException("El id de la Eps no puede estar vacio.");
        }
        if (epsRepository.findEpsByIdEps(idEps) == null) {
            throw new ResourceNotFoundException("La Eps con id " + idEps + " no existe o esta inactivo.");
        }
        Eps eps = epsRepository.findEpsByIdEps(idEps);
        if (lugarAtencionRepository.findAllByEpsCollectionAndAndEstadoLugarAtencionIsTrue(eps).isEmpty()    ){
            throw new ResourceNotFoundException("El Eps no tiene Lugares disponibles activos en el sistema");
        }
        return lugarAtencionRepository.findAllByEpsCollectionAndAndEstadoLugarAtencionIsTrue(eps);
    }

}
