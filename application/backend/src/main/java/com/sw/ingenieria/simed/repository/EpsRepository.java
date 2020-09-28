package com.sw.ingenieria.simed.repository;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface EpsRepository extends CrudRepository<Eps, Short>{
    Eps findEpsByIdEps(Short idEps);
    Eps findByIdEpsAndEstadoEpsTrue(Short idEps);
}
