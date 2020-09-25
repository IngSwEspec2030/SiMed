package com.sw.ingenieria.simed.service;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import com.sw.ingenieria.simed.repository.EpsRepository;
import com.sw.ingenieria.simed.repository.LugarAtencionRepository;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    public LugarAtencion update(LugarAtencion entity, Long id) throws Exception {
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

    public List<LugarAtencion> obtenerLugaresAtencionCercanos (Short idEps, double lat, double lon){
        List<LugarAtencion> lugarAtencionEPS = obtenerLugarAtencionPorEps(idEps);
        int cant = lugarAtencionEPS.size();
        Double distancia[] = new Double[cant];

        for (int i=0; i<cant;i++){
            double lon2 = lugarAtencionEPS.get(i).getLongitudLugarAtencion();
            double lat2 = lugarAtencionEPS.get(i).getLatitudLugarAtencion();
            distancia[i]=obtenerDistancia(lat,lon,lat2,lon2);
        }

        for (int i = 0; i < cant; i++) {
            for (int j = 0; j < cant - i - 1; j++) {
                if (distancia[j + 1] < distancia[j]) {
                    Double temp = distancia[j+1];
                    distancia[j+1] = distancia[j];
                    distancia[j] = temp;
                    LugarAtencion laTemp = lugarAtencionEPS.get(j+1);
                    lugarAtencionEPS.set(j+1,lugarAtencionEPS.get(j));
                    lugarAtencionEPS.set(j,laTemp);
                }
            }
        }
        //ToDo limitar elementos a 5 o menos
        int cantEnviar = 0;
        if(lugarAtencionEPS.size()>=5){
            cantEnviar=5;
        }else cantEnviar = lugarAtencionEPS.size();
        List<LugarAtencion> lugarAtencionCercanos = new ArrayList<LugarAtencion>();
        for(int i=0;i<cantEnviar;i++){
            lugarAtencionCercanos.add(lugarAtencionEPS.get(i));
        }
        return lugarAtencionCercanos;
    }

    private double obtenerDistancia(double lat1, double lon1, double lat2, double lon2){
        double difLat = (lat2-lat1)*Math.PI/180;
        double difLon = (lon2-lon1)*Math.PI/180;
        double a = Math.sin(difLat/2)*Math.sin(difLat/2) + Math.cos(lat1*Math.PI/180)*Math.cos(lat2*Math.PI/180)*Math.sin(difLon/2)*Math.sin(difLon/2);
        double dist = 12756*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return dist;
    }

}
