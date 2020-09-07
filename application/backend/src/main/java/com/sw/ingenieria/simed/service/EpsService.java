package com.sw.ingenieria.simed.service;


import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.EpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EpsService implements ServiceInterface  <Eps, Short>{
@Autowired
    private EpsRepository epsRepository;
    @Override
    public Eps findById(Short key) throws Exception {
        System.out.println(" eb ek eservucui key = " + key);
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("La EPS con id " + key + " no existe.");
        }
        return epsRepository.findById(key).get();
    }

    @Override
    public List<Eps> findAll() throws Exception {
        return (List <Eps>) epsRepository.findAll();
    }

    @Override
    public Eps create(Eps entity) throws Exception {
        entity.setEstadoEps(true);
        return epsRepository.save(entity);
    }

    @Override
    public Eps update(Eps entity) throws Exception {
        Eps eps = epsRepository.findById(entity.getIdEps()).get();
        return epsRepository.save(entity);
    }

    @Override
    public void delete(Short key) throws Exception {
        Eps eps = epsRepository.findById(key).get();
        eps.setEstadoEps(false);
        epsRepository.save(eps);
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
    public Eps activar(Short key) throws Exception {
        Eps eps = epsRepository.findById(key).get();
        eps.setEstadoEps(true);
        return epsRepository.save(eps);
    }
}
