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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johan Céspedes at PUJ
 */
@Entity
@Table(name = "ESPECIALIDAD")
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e")})
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESPECIALIDAD")
    private Short idEspecialidad;
    @Size(max = 100)
    @Column(name = "NOMBRE_ESPECIALIDAD")
    private String nombreEspecialidad;
    @JoinTable(name = "many_LUGAR_ATENCION_has_many_ESPECIALIDAD", joinColumns = {
        @JoinColumn(name = "ID_ESPECIALIDAD", referencedColumnName = "ID_ESPECIALIDAD")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_LUGARES_ATENCION", referencedColumnName = "ID_LUGARES_ATENCION")})
    @ManyToMany
    private Collection<LugarAtencion> lugarAtencionCollection;

    public Especialidad() {
    }

    public Especialidad(Short idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Short getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Short idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Collection<LugarAtencion> getLugarAtencionCollection() {
        return lugarAtencionCollection;
    }

    public void setLugarAtencionCollection(Collection<LugarAtencion> lugarAtencionCollection) {
        this.lugarAtencionCollection = lugarAtencionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecialidad != null ? idEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.idEspecialidad == null && other.idEspecialidad != null) || (this.idEspecialidad != null && !this.idEspecialidad.equals(other.idEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sw.ingenieria.simed.entity.Especialidad[ idEspecialidad=" + idEspecialidad + " ]";
    }
    
}
