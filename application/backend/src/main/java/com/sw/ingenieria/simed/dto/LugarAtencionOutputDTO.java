package com.sw.ingenieria.simed.dto;

import  com.sw.ingenieria.simed.entity.LugarAtencion;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 13/09/2020
 */
public class LugarAtencionOutputDTO {

    private Long idLugaresAtencion;
    private String nombreLugarAtencion;
    private String direccionLugarAtencion;
    private Double latitudLugarAtencion;
    private Double longitudLugarAtencion;
    private Boolean estadoLugarAtencion;
    private int disponibilidad;

    public LugarAtencionOutputDTO() {
    }

    public Long getIdLugaresAtencion() {
        return idLugaresAtencion;
    }

    public void setIdLugaresAtencion(Long idLugaresAtencion) {
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

    public Boolean getEstadoLugarAtencion() {
        return estadoLugarAtencion;
    }

    public void setEstadoLugarAtencion(Boolean estadoLugarAtencion) {
        this.estadoLugarAtencion = estadoLugarAtencion;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Metodo que permite modelar los datos a mostrar dependientes de la entidad LugaresAtencion.
     * @param lugarAtencion de un perfil de LugarAtencion
     * @return LugarAtencionOutputDTO
     */
    public static LugarAtencionOutputDTO getDTO(LugarAtencion lugarAtencion){
        LugarAtencionOutputDTO lugarAtencionOutputDTO = new LugarAtencionOutputDTO();
        lugarAtencionOutputDTO.setIdLugaresAtencion(lugarAtencion.getIdLugaresAtencion());
        lugarAtencionOutputDTO.setNombreLugarAtencion(lugarAtencion.getNombreLugarAtencion());
        lugarAtencionOutputDTO.setDireccionLugarAtencion(lugarAtencion.getDireccionLugarAtencion());
        lugarAtencionOutputDTO.setEstadoLugarAtencion(lugarAtencion.getEstadoLugarAtencion());
        lugarAtencionOutputDTO.setLatitudLugarAtencion(lugarAtencion.getLatitudLugarAtencion());
        lugarAtencionOutputDTO.setLongitudLugarAtencion(lugarAtencion.getLongitudLugarAtencion());
        int disponibilidad = (int)(Math.random()*100+1);

        lugarAtencionOutputDTO.setDisponibilidad(disponibilidad);
        return lugarAtencionOutputDTO;
    }







}
