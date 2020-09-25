/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Table(name = "\"TIPO_IDENTIFICACION\"")
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t")})
public class TipoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_IDENTIFICACION")
    private Short tipoIdentificacion;
    @Size(max = 100)
    @Column(name = "NOMBRE_TIPO_IDENTIFICACION")
    private String nombreTipoIdentificacion;
    @OneToMany(mappedBy = "tipoIdentificacion")
    @JsonIgnore
    @JsonDeserialize
    @ToString.Exclude
    private Collection<Usuario> usuarioCollection;

    public TipoIdentificacion(Short idTipoIdentificacion) {
        this.tipoIdentificacion = idTipoIdentificacion;
    }

    
}
