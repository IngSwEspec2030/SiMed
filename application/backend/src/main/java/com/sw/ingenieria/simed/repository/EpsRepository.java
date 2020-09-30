package com.sw.ingenieria.simed.repository;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.LugarAtencion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EpsRepository extends CrudRepository<Eps, Short>{
    Eps findEpsByIdEps(Short idEps);
    Eps findByIdEpsAndEstadoEpsTrue(Short idEps);


    @Query(value=" SELECT COUNT(\"simedSch\".\"USUARIO\".\"ID_EPS\") AS TOTAL,\n" +
            "\"simedSch\".\"EPS\".\"NOMBRE_EPS\" AS NOMBRE_EPS\n" +
            "FROM \"simedSch\".\"USUARIO\"\n" +
            "JOIN \"simedSch\".\"EPS\" ON \"simedSch\".\"USUARIO\".\"ID_EPS\" = \"simedSch\".\"EPS\".\"ID_EPS\"\n" +
            "GROUP BY \"simedSch\".\"EPS\".\"NOMBRE_EPS\"",nativeQuery = true)
   List <Map<String, String>> findAllByEps();




}
