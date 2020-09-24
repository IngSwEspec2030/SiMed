package com.sw.ingenieria.simed.dto;

import com.sw.ingenieria.simed.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioInputDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String username;
    private String numeroIdentificacionUsuario;
    private String passwordUsuario;
    private String correoUsuario;
    private Boolean estadoUsuario;
    private LocalDate fechaCreacionUsuario;
    private LocalDate fechaModificacionUsuario;
    private Short tipoIdentif;
    private Short tipoUsuar;
    private Short ep;

}