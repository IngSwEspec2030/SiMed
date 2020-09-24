/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw.ingenieria.simed.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "\"ESPECIALIDAD\"")
/*
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e")})
*/
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESPECIALIDAD")
    private Short idEspecialidad;
    @Size(max = 100)
    @Column(name = "NOMBRE_ESPECIALIDAD")
    private String nombreEspecialidad;
    @Column(name = "ESTADO_ESPECIALIDAD")
    private Boolean estadoEspecialidad;
    @JoinTable(name = "LUGAR_ATENCION_X_ESPECIALIDAD", joinColumns = {
        @JoinColumn(name = "ID_ESPECIALIDAD", referencedColumnName = "ID_ESPECIALIDAD")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_LUGARES_ATENCION", referencedColumnName = "ID_LUGARES_ATENCION")})
    @ManyToMany
    @ToString.Exclude
    private Collection<LugarAtencion> lugarAtencionCollection;

    public Especialidad(Short idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
    
}
