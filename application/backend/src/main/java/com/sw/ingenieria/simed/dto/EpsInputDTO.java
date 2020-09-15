package com.sw.ingenieria.simed.dto;

import com.sw.ingenieria.simed.entity.LugarAtencion;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Collection;

/**
 * @author Johan Céspedes Ortega
 * @date 14/09/2020
 * Clase DTO para las entradas de Lugares de Atención para una EPS.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EpsInputDTO {

    private Short idEps;
    @Size(max = 100)
    private String nombreEps;
    @Size(max = 100)
    private String direccionEps;
    @Size(max = 75)
    private Boolean estadoEps;
    @Size(max = 100)
    private String direccion;
    @Size(max = 15)
    private String telefonoEps;
    @Size(max = 100)

    //    Relaciones
    @ToString.Exclude
    private Collection<LugarAtencion> lugarAtencionCollection;

}
