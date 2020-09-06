package com.sw.ingenieria.simed.service;

import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.LugarAtencionRepository;


import java.time.LocalDate;
import java.util.List;


public class LugarAtencionService implements ServiceInterface <LugarAtencion, Long> {
    private LugarAtencionRepository lugarAtencionRepository;

    public LugarAtencionService(LugarAtencionRepository lugarAtencionRepository) {
        this.lugarAtencionRepository = lugarAtencionRepository;
    }


    @Override
    public LugarAtencion findById(Long key) throws Exception {
        System.out.println("este es el id que llega al servicio = " + key);
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El lugar de atención con id " + key + " no existe.");
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
        lugarAtencionRepository.delete(lugarAtencion);

    }

    public Boolean existeById (Long key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar no puede estar vacío.");
        }
        if (lugarAtencionRepository.findById(key) == null) {
            return false;
        }
        return lugarAtencionRepository.existsById(key);
    }

    public LugarAtencion findId(Long key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El lugar con ID " + key + " no existe.");
        }
        return lugarAtencionRepository.findById(key).get();
    }


}
