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

    
}
