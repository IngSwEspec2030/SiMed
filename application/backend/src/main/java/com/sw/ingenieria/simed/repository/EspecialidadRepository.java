package com.sw.ingenieria.simed.repository;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.Especialidad;
import org.springframework.data.repository.CrudRepository;

public interface EspecialidadRepository extends CrudRepository<Especialidad, Short>{

    Especialidad findByIdEspecialidadAndEstadoEspecialidadTrue(Short idEsp);

}
