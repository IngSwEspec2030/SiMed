/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LUGAR_ATENCION")
@NamedQueries({
    @NamedQuery(name = "LugarAtencion.findAll", query = "SELECT l FROM LugarAtencion l")})
public class LugarAtencion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LUGARES_ATENCION")
    private Long idLugaresAtencion;
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
    @Column(name = "ESTADO_LUGAR_ATENCION")
    private Boolean estadoLugarAtencion;
    /*@ManyToMany(mappedBy = "lugarAtencionCollection")
    private Collection<Especialidad> especialidadCollection;
    @ManyToMany(mappedBy = "lugarAtencionCollection")

    private Collection<Eps> epsCollection;*/


    public LugarAtencion(Long idLugaresAtencion) {
        this.idLugaresAtencion = idLugaresAtencion;
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
