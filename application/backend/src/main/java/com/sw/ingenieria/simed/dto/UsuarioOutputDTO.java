package com.sw.ingenieria.simed.dto;

import com.sw.ingenieria.simed.entity.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */

@NoArgsConstructor
@Data
public class UsuarioOutputDTO {

    private Long idUsuario;
    private String username;
    private String nombreUsuario;
    private String apellidosUsuario;
    private String numeroIdentificacionUsuario;
    private String correoUsuario;
    private LocalDate fechaCreacionUsuario;
    private LocalDate fechaModificacionUsuario;

    //Relaciones
    private Map<String,Object> tipoUsuario;
    private Map<String,Object> eps;
    private Map<String,Object> tipoIdentificacion;

    //Auditoria
    private boolean estadoUsuario;

   /**
     * Metodo que permite optener información puntual de un registro de Usuario.
     * @param usuario Objeto Usuario
     * @return UsuarioOutputDTO
     */
    public static UsuarioOutputDTO getDTO(Usuario usuario){
        UsuarioOutputDTO usuDTO = new UsuarioOutputDTO();
        usuDTO.setIdUsuario(usuario.getIdUsuario());
        usuDTO.setUsername(usuario.getUsername());
        usuDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuDTO.setApellidosUsuario(usuario.getApellidosUsuario());
        usuDTO.setNumeroIdentificacionUsuario(usuario.getNumeroIdentificacionUsuario());
        usuDTO.setCorreoUsuario(usuario.getCorreoUsuario());
        usuDTO.setEstadoUsuario(usuario.getEstadoUsuario());
        usuDTO.setFechaCreacionUsuario(usuario.getFechaCreacionUsuario());
        usuDTO.setFechaModificacionUsuario(usuario.getFechaModificacionUsuario());

        if(usuario.getTipoUsuario() !=null) {
            Map<String, Object> usuarioData = new LinkedHashMap<>();
            usuarioData.put("nombreTipoUsuario",usuario.getTipoUsuario().getNombreTipoUsuario());
            usuarioData.put("idTipoUsuario", usuario.getTipoUsuario().getIdTipoUsuario());
            usuDTO.setTipoUsuario(usuarioData);
        }

       if(usuario.getEps() !=null) {
            Map<String, Object> usuarioData = new LinkedHashMap<>();
            usuarioData.put("nombreEps",usuario.getEps().getNombreEps());
            usuarioData.put("idEps", usuario.getEps().getIdEps());
            usuDTO.setEps(usuarioData);
        }
        if(usuario.getTipoIdentificacion() !=null) {
            Map<String, Object> usuarioData = new LinkedHashMap<>();
            usuarioData.put("nombreTipoIdentificacion",usuario.getTipoIdentificacion().getNombreTipoIdentificacion());
            usuarioData.put("tipoIdentificacion", usuario.getTipoIdentificacion().getTipoIdentificacion());
            usuDTO.setTipoIdentificacion(usuarioData);
        }

        return usuDTO;
    }

}

