package com.sw.ingenieria.simed.repository;

import com.sw.ingenieria.simed.entity.Eps;
import com.sw.ingenieria.simed.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.List;
public interface UsuarioRepository extends CrudRepository <Usuario, Long>{
    Optional<Usuario> findByUsernameAndEstadoUsuarioTrue (String username);
}
