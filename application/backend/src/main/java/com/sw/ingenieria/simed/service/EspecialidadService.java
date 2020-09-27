package com.sw.ingenieria.simed.service;


import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.Especialidad;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.EpsRepository;
import com.sw.ingenieria.simed.repository.EspecialidadRepository;
import com.sw.ingenieria.simed.repository.LugarAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EspecialidadService implements ServiceInterface  <Especialidad, Short>{
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private EpsRepository epsRepository;

    @Autowired
    private LugarAtencionRepository lugarAtencionRepository;

    @Autowired
    private LugarAtencionService lugarAtencionService;



    @Override
    public Especialidad findById(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La Especialidad con id " + key + " no existe.");
        }
        return especialidadRepository.findById(key).get();
    }

    @Override
    public List<Especialidad> findAll() throws Exception {
        return (List <Especialidad>) especialidadRepository.findAll();
    }

    @Override
    public Especialidad create(Especialidad entity) throws Exception {
        entity.setEstadoEspecialidad(true);
        return especialidadRepository.save(entity);
    }

    @Override
    public Especialidad update(Especialidad entity) throws Exception {
        Especialidad especialidad = especialidadRepository.findById(entity.getIdEspecialidad()).get();

        if (entity.getEstadoEspecialidad() != null) {
            especialidad.setEstadoEspecialidad(entity.getEstadoEspecialidad());
        }
        if (entity.getNombreEspecialidad() != null) {
            especialidad.setNombreEspecialidad(entity.getNombreEspecialidad());
        }
       /* if (entity.getLugarAtencionCollection() != null) {
            asignarLugar(entity);
        }*/
        return especialidadRepository.save(entity);
    }

    @Override
    public void delete(Short key) throws Exception {
    }

    public Especialidad deleteEspecialidad(Short key) throws Exception {
        Especialidad especialidad = especialidadRepository.findById(key).get();
        especialidad.setEstadoEspecialidad(false);
       return especialidadRepository.save(especialidad);
    }

    public Boolean existeById (Short key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (especialidadRepository.findById(key) == null) {
            return false;
        }
        return especialidadRepository.existsById(key);
    }

    public Especialidad findId(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La Especialidad con id " + key + " no existe.");
        }
        return especialidadRepository.findById(key).get();
    }

    /**
     * Metodo para activar Especialidades
     * @param key id de Especialidad
     * @return
     * @throws Exception
     */
    public Especialidad activar(Short key) throws Exception {
        Especialidad especialidad = especialidadRepository.findById(key).get();
        especialidad.setEstadoEspecialidad(true);
        return especialidadRepository.save(especialidad);
    }

    /**
     * @param entity Objeto EpsInputDTO
     * @return Objeto EPS
     * @autor Johan Miguel Céspedes - Método que permite asignar uno o muchos Lugares de Atencion a una EPS.
     */
    public Especialidad asignarLugar(Especialidad entity) throws Exception {
        if (entity.getIdEspecialidad() == null || !existeById(entity.getIdEspecialidad())) {
            throw new ResourceNotFoundException("La Especialidad con Id " + entity.getIdEspecialidad() + " no existe.");
        }
        Especialidad esp = especialidadRepository.findByIdEspecialidadAndEstadoEspecialidadTrue(entity.getIdEspecialidad());
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
        esp.setLugarAtencionCollection(entity.getLugarAtencionCollection());
        return especialidadRepository.save(esp);
    }
}
