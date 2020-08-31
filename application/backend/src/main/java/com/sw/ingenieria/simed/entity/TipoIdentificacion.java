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
@Table(name = "TIPO_IDENTIFICACION")
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t")})
public class TipoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_IDENTIFICACION")
    private Short idTipoIdentificacion;
    @Size(max = 100)
    @Column(name = "NOMBRE_TIPO_IDENTIFICACION")
    private String nombreTipoIdentificacion;
    @OneToMany(mappedBy = "idTipoIdentificacion")
    private Collection<Usuario> usuarioCollection;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(Short idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public Short getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Short idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public String getNombreTipoIdentificacion() {
        return nombreTipoIdentificacion;
    }

    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoIdentificacion != null ? idTipoIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoIdentificacion)) {
            return false;
        }
        TipoIdentificacion other = (TipoIdentificacion) object;
        if ((this.idTipoIdentificacion == null && other.idTipoIdentificacion != null) || (this.idTipoIdentificacion != null && !this.idTipoIdentificacion.equals(other.idTipoIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sw.ingenieria.simed.entity.TipoIdentificacion[ idTipoIdentificacion=" + idTipoIdentificacion + " ]";
    }
    
}
