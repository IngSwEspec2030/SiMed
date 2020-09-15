package com.sw.ingenieria.simed.service;


import com.sw.ingenieria.simed.entity.Especialidad;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EspecialidadService implements ServiceInterface  <Especialidad, Short>{
@Autowired
    private EspecialidadRepository especialidadRepository;
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
        //entity.setEstadoEspeciaidad(true);
        return especialidadRepository.save(entity);
    }

    @Override
    public Especialidad update(Especialidad entity) throws Exception {
        Especialidad especialidad = especialidadRepository.findById(entity.getIdEspecialidad()).get();
        return especialidadRepository.save(entity);
    }

    @Override
    public void delete(Short key) throws Exception {
        Especialidad especialidad = especialidadRepository.findById(key).get();
        //especialidad.setEstadoEspeciaidad(false);
        especialidadRepository.save(especialidad);
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
        //especialidad.setEstadoEspecialidad(true);
        return especialidadRepository.save(especialidad);
    }
}
