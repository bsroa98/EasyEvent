package com.ucatolica.easyevent.easyevent.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PersonalizationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String descripcion;
    private String date;
    private String actividades;
    private String ubicacion;
    private String comida;
    private String categoria;
    private Integer capacidad;


    public PersonalizationEvent(String date, String descripcion, String actividades, String ubicacion, String categoria, Integer capacidad, String comida)
    {
        this.date = date;
        this.descripcion = descripcion;
        this.actividades = actividades;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.capacidad = capacidad;
        this.comida = comida;
    }// constructor



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
