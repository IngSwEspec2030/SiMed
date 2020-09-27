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
@Entity
@Table(name = "\"LUGAR_ATENCION\"")
@NamedQueries({
        @NamedQuery(name = "LugarAtencion.findAll", query = "SELECT l FROM LugarAtencion l")})
public class LugarAtencion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(mappedBy = "lugarAtencionCollection")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Especialidad> especialidadCollection;
    @ManyToMany(mappedBy = "lugarAtencionCollection")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Eps> epsCollection;
    public LugarAtencion(Long idLugaresAtencion) {
        this.idLugaresAtencion = idLugaresAtencion;
    }

}
