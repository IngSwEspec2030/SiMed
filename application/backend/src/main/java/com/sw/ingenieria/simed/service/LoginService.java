package com.sw.ingenieria.simed.service;

import com.sw.ingenieria.simed.dto.LoginInput;
import com.sw.ingenieria.simed.dto.LoginOutput;
import com.sw.ingenieria.simed.dto.UsuarioOutputDTO;
import com.sw.ingenieria.simed.entity.Usuario;
import com.sw.ingenieria.simed.exeptions.JWTException;
import com.sw.ingenieria.simed.exeptions.ServerErrorException;
import com.sw.ingenieria.simed.exeptions.UnauthorizedException;
import com.sw.ingenieria.simed.util.EncoderUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */

@Service
public class LoginService {

    private final UsuarioService usuarioService;

    private final JWTService jwtService;

    public LoginService(UsuarioService usuarioService, JWTService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    public LoginOutput login(LoginInput loginInput) throws Exception {
        LoginOutput loginOutput = new LoginOutput();
        Optional<Usuario> usuarioOp = usuarioService.findByUsernameAndActivoTrue(loginInput.getUsername());
        if(! usuarioOp.isPresent())
            throw new UnauthorizedException("Credenciales erroneas.");
        else {
            Usuario usuario = usuarioOp.get();
            if(! validatePassword(loginInput, usuario))
                throw new UnauthorizedException("Credenciales erroneas.");
            else {
                try {
                    String token = jwtService.generateToken(usuario.getUsername());
                    loginOutput.setStatus(200);
                    loginOutput.setMessage("Bienvenido");
                    loginOutput.setToken(token);
                    loginOutput.setUsuario(UsuarioOutputDTO.getDTO(usuario));
                } catch (JWTException e) {
                    e.printStackTrace();
                    throw new ServerErrorException("Descripción del Error:"+e.getMessage());
                }
            }
        }
        return loginOutput;
    }

    private boolean validatePassword(LoginInput input, Usuario usuario){
        return EncoderUtil.validate(input.getPassword(), usuario.getPasswordUsuario());
    }

}
