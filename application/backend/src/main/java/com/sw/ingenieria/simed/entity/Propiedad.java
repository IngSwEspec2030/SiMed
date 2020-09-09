/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johan Céspedes at PUJ
 */
@Entity
@Table(name = "PROPIEDAD")
@NamedQueries({
    @NamedQuery(name = "Propiedad.findAll", query = "SELECT p FROM Propiedad p")})
public class Propiedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROPIEDAD")
    private Short idPropiedad;
    @Column(name = "ESTADO_PROPIEDAD")
    private Boolean estadoPropiedad;
    @Size(max = 250)
    @Column(name = "NOMBRE_PROPIEDAD")
    private String nombrePropiedad;
    @Size(max = 2000)
    @Column(name = "VALOR_PROPIEDAD")
    private String valorPropiedad;

    public Propiedad() {
    }

    public Propiedad(Short idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Short getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Short idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Boolean getEstadoPropiedad() {
        return estadoPropiedad;
    }

    public void setEstadoPropiedad(Boolean estadoPropiedad) {
        this.estadoPropiedad = estadoPropiedad;
    }

    public String getNombrePropiedad() {
        return nombrePropiedad;
    }

    public void setNombrePropiedad(String nombrePropiedad) {
        this.nombrePropiedad = nombrePropiedad;
    }

    public String getValorPropiedad() {
        return valorPropiedad;
    }

    public void setValorPropiedad(String valorPropiedad) {
        this.valorPropiedad = valorPropiedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropiedad != null ? idPropiedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propiedad)) {
            return false;
        }
        Propiedad other = (Propiedad) object;
        if ((this.idPropiedad == null && other.idPropiedad != null) || (this.idPropiedad != null && !this.idPropiedad.equals(other.idPropiedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sw.ingenieria.simed.entity.Propiedad[ idPropiedad=" + idPropiedad + " ]";
    }
    
}
