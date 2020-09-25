package com.sw.ingenieria.simed.service;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.UsuarioRepository;
import com.sw.ingenieria.simed.entity.Usuario;
import com.sw.ingenieria.simed.util.EncoderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */

@Service
public class UsuarioService implements ServiceInterface <Usuario, Long>{

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario findById(Long key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El Usuario con id " + key + " no existe.");
        }
        return usuarioRepository.findById(key).get();

    }

    @Override
    public List<Usuario> findAll() throws Exception {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario create(Usuario entity) throws Exception {
        entity.setFechaCreacionUsuario(LocalDate.now());
        entity.setFechaModificacionUsuario(LocalDate.now());
        entity.setUsername(entity.getUsername().trim().toLowerCase());
        entity.setPasswordUsuario(EncoderUtil.encode(entity.getPasswordUsuario()));
        return  usuarioRepository.save(entity);
    }

    @Override
    public Usuario update(Usuario entity, Long key) throws Exception {
        Usuario usuario = usuarioRepository.findById(key).get();

        if (entity.getFechaCreacionUsuario() == null) {
            entity.setFechaCreacionUsuario(usuario.getFechaCreacionUsuario());
        }
        if (entity.getFechaModificacionUsuario() == null) {
            entity.setFechaModificacionUsuario(LocalDate.now());
        }
        if (entity.getPasswordUsuario() == null) {
            entity.setPasswordUsuario(usuario.getPasswordUsuario());

        } else {
            entity.setPasswordUsuario(EncoderUtil.encode(entity.getPasswordUsuario()));
        }
        if (entity.getEps() == null) {
            entity.setEps(usuario.getEps());
        }
        if (entity.getTipoIdentificacion() == null) {
            entity.setTipoIdentificacion(usuario.getTipoIdentificacion());
        }
        if (entity.getTipoUsuario() == null) {
            entity.setTipoUsuario(usuario.getTipoUsuario());
        }

        if (entity.getUsername() == null) {
            entity.setUsername(usuario.getUsername());
        }

        if (entity.getApellidosUsuario() == null) {
            entity.setApellidosUsuario(usuario.getApellidosUsuario());
        }
        if (entity.getNombreUsuario() == null) {
            entity.setNombreUsuario(usuario.getNombreUsuario());
        }
        if (entity.getCorreoUsuario() == null) {
            entity.setCorreoUsuario(usuario.getCorreoUsuario());
        }
        if (entity.getEstadoUsuario() == null) {
            entity.setEstadoUsuario(usuario.getEstadoUsuario());
        }
        if (entity.getNumeroIdentificacionUsuario() == null) {
            entity.setNumeroIdentificacionUsuario(usuario.getNumeroIdentificacionUsuario());
        }




        return usuarioRepository.save(entity);
    }

    @Override
    public void delete(Long key) throws Exception {
    }

    public Usuario deleteUsuario(Long key) throws Exception {
        Usuario usuario = usuarioRepository.findById(key).get();
        usuario.setEstadoUsuario(false);
        usuario.setFechaModificacionUsuario(LocalDate.now());
        return usuarioRepository.save(usuario);
    }



    public Boolean existeById (Long key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (usuarioRepository.findById(key) == null) {
            return false;
        }
        return usuarioRepository.existsById(key);
    }

    public Usuario findId(Long key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El Usuario con id " + key + " no existe.");
        }
        return usuarioRepository.findById(key).get();
    }

    /**
     * Metodo para activar usuarios
     * @param key id del usuario
     * @return
     * @throws Exception
     */
    public Usuario activar(Long key) throws Exception {
        Usuario usuario = usuarioRepository.findById(key).get();
        usuario.setEstadoUsuario(true);
        usuario.setFechaModificacionUsuario(LocalDate.now());
        return usuarioRepository.save(usuario);
    }

    /**
     * Metodo que permite obtener el usuario apartir de su nombre de usuario.
     * @param username Nombre de usuario a validar.
     * @return Objeto Usuario.
     */
    public Optional<Usuario> findByUsernameAndActivoTrue (String username) {
        if(username == null){
            throw new ResourceNotFoundException("El Username a validar, no puede estar vacio.");
        }
        return usuarioRepository.findByUsernameAndEstadoUsuarioTrue(username.trim().toLowerCase());
    }


}
