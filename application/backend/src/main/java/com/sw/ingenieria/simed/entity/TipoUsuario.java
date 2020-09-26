/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johan Céspedes at PUJ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"TIPO_USUARIO\"")
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_USUARIO")
    private Short idTipoUsuario;
    @Size(max = 100)
    @Column(name = "NOMBRE_TIPO_USUARIO")
    private String nombreTipoUsuario;
    @OneToMany(mappedBy = "tipoUsuario")
    private Collection<Usuario> usuarioCollection;

    public TipoUsuario(Short idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

}
