package com.sw.ingenieria.simed.repository;
import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface LugarAtencionRepository extends CrudRepository<LugarAtencion, Long> {
    List<LugarAtencion> findAllByEpsCollectionAndAndEstadoLugarAtencionIsTrue(Eps eps);
}
