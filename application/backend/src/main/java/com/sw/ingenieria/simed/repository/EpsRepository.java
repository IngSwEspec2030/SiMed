package com.sw.ingenieria.simed.repository;

import com.sw.ingenieria.simed.entity.Eps;
import org.springframework.data.repository.CrudRepository;

public interface EpsRepository extends CrudRepository<Eps, Short>{
    Eps findEpsByIdEps(Short idEps);
    Eps findByIdEpsAndEstadoEpsTrue(Short idEps);
}
