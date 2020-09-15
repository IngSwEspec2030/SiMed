package com.sw.ingenieria.simed.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */
public class LoginInput {

    @NotNull(message = "El Nombre de usuario no puede estar vacio.")
    private String username;
    @NotNull(message = "La Contraseña no puede estar vacia.")
    private String password;

    public LoginInput(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
