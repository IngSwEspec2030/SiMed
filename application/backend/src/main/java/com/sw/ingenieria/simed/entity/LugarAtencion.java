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
@Table(name = "LUGAR_ATENCION")
@NamedQueries({
    @NamedQuery(name = "LugarAtencion.findAll", query = "SELECT l FROM LugarAtencion l")})
public class LugarAtencion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LUGARES_ATENCION")
    private Short idLugaresAtencion;
    @Size(max = 100)
    @Column(name = "NOMBRE_LUGAR_ATENCION")
    private String nombreLugarAtencion;
    @Size(max = 100)
    @Column(name = "DIRECCION_LUGAR_ATENCION")
    private String direccionLugarAtencion;
    @Size(max = 20)
    @Column(name = "TELEFONO_LUGAR_ATENCION")
    private String telefonoLugarAtencion;
    @Size(max = 100)
    @Column(name = "SITIO_WEB_LUGAR_ATENCION")
    private String sitioWebLugarAtencion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUD_LUGAR_ATENCION")
    private Double latitudLugarAtencion;
    @Column(name = "LONGITUD_LUGAR_ATENCION")
    private Double longitudLugarAtencion;
    @ManyToMany(mappedBy = "lugarAtencionCollection")
    private Collection<Especialidad> especialidadCollection;
    @ManyToMany(mappedBy = "lugarAtencionCollection")
    private Collection<Eps> epsCollection;

    public LugarAtencion() {
    }

    public LugarAtencion(Short idLugaresAtencion) {
        this.idLugaresAtencion = idLugaresAtencion;
    }

    public Short getIdLugaresAtencion() {
        return idLugaresAtencion;
    }

    public void setIdLugaresAtencion(Short idLugaresAtencion) {
        this.idLugaresAtencion = idLugaresAtencion;
    }

    public String getNombreLugarAtencion() {
        return nombreLugarAtencion;
    }

    public void setNombreLugarAtencion(String nombreLugarAtencion) {
        this.nombreLugarAtencion = nombreLugarAtencion;
    }

    public String getDireccionLugarAtencion() {
        return direccionLugarAtencion;
    }

    public void setDireccionLugarAtencion(String direccionLugarAtencion) {
        this.direccionLugarAtencion = direccionLugarAtencion;
    }

    public String getTelefonoLugarAtencion() {
        return telefonoLugarAtencion;
    }

    public void setTelefonoLugarAtencion(String telefonoLugarAtencion) {
        this.telefonoLugarAtencion = telefonoLugarAtencion;
    }

    public String getSitioWebLugarAtencion() {
        return sitioWebLugarAtencion;
    }

    public void setSitioWebLugarAtencion(String sitioWebLugarAtencion) {
        this.sitioWebLugarAtencion = sitioWebLugarAtencion;
    }

    public Double getLatitudLugarAtencion() {
        return latitudLugarAtencion;
    }

    public void setLatitudLugarAtencion(Double latitudLugarAtencion) {
        this.latitudLugarAtencion = latitudLugarAtencion;
    }

    public Double getLongitudLugarAtencion() {
        return longitudLugarAtencion;
    }

    public void setLongitudLugarAtencion(Double longitudLugarAtencion) {
        this.longitudLugarAtencion = longitudLugarAtencion;
    }

    public Collection<Especialidad> getEspecialidadCollection() {
        return especialidadCollection;
    }

    public void setEspecialidadCollection(Collection<Especialidad> especialidadCollection) {
        this.especialidadCollection = especialidadCollection;
    }

    public Collection<Eps> getEpsCollection() {
        return epsCollection;
    }

    public void setEpsCollection(Collection<Eps> epsCollection) {
        this.epsCollection = epsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLugaresAtencion != null ? idLugaresAtencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugarAtencion)) {
            return false;
        }
        LugarAtencion other = (LugarAtencion) object;
        if ((this.idLugaresAtencion == null && other.idLugaresAtencion != null) || (this.idLugaresAtencion != null && !this.idLugaresAtencion.equals(other.idLugaresAtencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sw.ingenieria.simed.entity.LugarAtencion[ idLugaresAtencion=" + idLugaresAtencion + " ]";
    }
    
}
