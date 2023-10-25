package com.ucatolica.easyevent.easyevent.entities;

import jakarta.persistence.*;
@Entity
@Table(name = "ofertaUsuario")
public class offerUser {

    public offerUser() {
    }

    public offerUser(String nombreContacto, String correoContacto, int telefonoContacto, String experiencia) {
        this.nombreContacto = nombreContacto;
        this.correoContacto = correoContacto;
        this.telefonoContacto = telefonoContacto;
        this.experiencia = experiencia;
    }

    @Id
    @Column(name = "nombreContacto", length = 50)
    private String nombreContacto;

    @Column(name = "correoContacto", length = 50)
    private String correoContacto;

    @Column(name = "telefonoContacto", length = 15)
    private int telefonoContacto;

    @Column(name = "experiencia", length = 500)
    private String experiencia;


    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
