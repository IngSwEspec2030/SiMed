package com.sw.ingenieria.simed.dto;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */


public class LoginOutput extends AbstractOutput {

    private UsuarioOutputDTO usuario;
    private String token;




    public LoginOutput() {
    }

    public UsuarioOutputDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioOutputDTO usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
