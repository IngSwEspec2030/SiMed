package com.sw.ingenieria.simed.dto;

import com.sw.ingenieria.simed.entity.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */

@NoArgsConstructor
@Getter
@Setter
public class UsuarioOutputDTO {

    private Long idUsuario;
    private String username;

    //Relaciones
    private Map<String,Object> nombreTipoUsuario;

    //Auditoria
    private boolean activo;

   /**
     * Metodo que permite optener información puntual de un registro de Usuario.
     * @param usuario Objeto Usuario
     * @return UsuarioOutputDTO
     */
    public static UsuarioOutputDTO getDTO(Usuario usuario){
        UsuarioOutputDTO usuDTO = new UsuarioOutputDTO();
        usuDTO.setIdUsuario(usuario.getIdUsuario());
        usuDTO.setUsername(usuario.getUsername());
        usuDTO.setActivo(usuario.getEstadoUsuario());

        if(usuario.getIdTipoUsuario() !=null) {
            Map<String, Object> usuarioData = new LinkedHashMap<>();
            usuarioData.put("nombreTipoUsuario",usuario.getIdTipoUsuario().getNombreTipoUsuario());
            usuDTO.setNombreTipoUsuario(usuarioData);
        }

        return usuDTO;
    }

}

