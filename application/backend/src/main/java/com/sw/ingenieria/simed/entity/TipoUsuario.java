/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johan Céspedes at PUJ
 */
@Entity
@Table(name = "TIPO_USUARIO")
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_USUARIO")
    private Short idTipoUsuario;
    @Size(max = 100)
    @Column(name = "NOMBRE_TIPO_USUARIO")
    private String nombreTipoUsuario;
    @OneToMany(mappedBy = "idTipoUsuario")
    private Collection<Usuario_1> usuarioCollection;

    public TipoUsuario() {
    }

    public TipoUsuario(Short idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Short getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Short idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public Collection<Usuario_1> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario_1> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.idTipoUsuario == null && other.idTipoUsuario != null) || (this.idTipoUsuario != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sw.ingenieria.simed.entity.TipoUsuario[ idTipoUsuario=" + idTipoUsuario + " ]";
    }
    
}
