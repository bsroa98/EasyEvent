package com.ucatolica.easyevent.easyevent.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "oferta_usuario")
public class FinalBocanegra {

    public FinalBocanegra() {
    }

    public FinalBocanegra(String nombreContacto, String correoContacto) {
        this.nombreContacto = nombreContacto;
        this.correoContacto = correoContacto;
    }

    @Id
    @Column(name = "nombre_contacto", length = 50)
    private String nombreContacto;

    @Column(name = "correo_contacto", length = 50)
    private String correoContacto;

    public FinalBocanegra(String nombreContacto, String correoContacto) {
    }


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
}