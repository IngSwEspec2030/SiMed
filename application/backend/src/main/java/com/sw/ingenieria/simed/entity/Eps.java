/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Getter
@Setter
@Entity
@Table(name = "\"EPS\"")
@NamedQueries({
    @NamedQuery(name = "Eps.findAll", query = "SELECT e FROM Eps e")})
public class Eps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EPS")
    private Short idEps;
    @Size(max = 100)
    @Column(name = "NOMBRE_EPS")
    private String nombreEps;
    @Size(max = 100)
    @Column(name = "DIRECCION_EPS")
    private String direccionEps;
    @Column(name = "ESTADO_EPS")
    private Boolean estadoEps;
    @Size(max = 100)
    @Column(name = "TELEFONO_EPS")
    private String telefonoEps;
    @JoinTable(name = "\"LUGAR_ATENCION_X_EPS\"", joinColumns = {
        @JoinColumn(name = "ID_EPS", referencedColumnName = "ID_EPS")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_LUGARES_ATENCION", referencedColumnName = "ID_LUGARES_ATENCION")})
    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    private Collection<LugarAtencion> lugarAtencionCollection;
    @OneToMany(mappedBy = "idEps")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Usuario> usuarioCollection;


    public Eps(Short idEps) {
        this.idEps = idEps;
    }
/*
    public Short getIdEps() {
        return idEps;
    }

    public void setIdEps(Short idEps) {
        this.idEps = idEps;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    public String getDireccionEps() {
        return direccionEps;
    }

    public void setDireccionEps(String direccionEps) {
        this.direccionEps = direccionEps;
    }

    public String getTelefonoEps() {
        return telefonoEps;
    }

    public void setTelefonoEps(String telefonoEps) {
        this.telefonoEps = telefonoEps;
    }

    public Collection<LugarAtencion> getLugarAtencionCollection() {
        return lugarAtencionCollection;
    }

    public void setLugarAtencionCollection(Collection<LugarAtencion> lugarAtencionCollection) {
        this.lugarAtencionCollection = lugarAtencionCollection;
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
        hash += (idEps != null ? idEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eps)) {
            return false;
        }
        Eps other = (Eps) object;
        if ((this.idEps == null && other.idEps != null) || (this.idEps != null && !this.idEps.equals(other.idEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sw.ingenieria.simed.entity.Eps[ idEps=" + idEps + " ]";
    }*/
    
}
