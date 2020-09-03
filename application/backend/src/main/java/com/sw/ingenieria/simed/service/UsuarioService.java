package com.sw.ingenieria.simed.service;

import com.sw.ingenieria.simed.exeptions.ResourceNotFoundException;
import com.sw.ingenieria.simed.repository.UsuarioRepository;
import com.sw.ingenieria.simed.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service
public class UsuarioService implements ServiceInterface <Usuario, Long>{

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario findById(Long key) throws Exception {
        System.out.println(" eb ek eservucui key = " + key);
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
        return  usuarioRepository.save(entity);
    }

    @Override
    public Usuario update(Usuario entity) throws Exception {
        Usuario usuario = usuarioRepository.findById(entity.getIdUsuario()).get();
        entity.setFechaCreacionUsuario(usuario.getFechaCreacionUsuario());
        return usuarioRepository.save(entity);
    }

    @Override
    public void delete(Long key) throws Exception {
        Usuario usuario = usuarioRepository.findById(key).get();
        usuario.setEstadoUsuario(false);
        usuario.setFechaModificacionUsuario(LocalDate.now());
        usuarioRepository.save(usuario);
        //usuarioRepository.delete(usuario);

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

}
