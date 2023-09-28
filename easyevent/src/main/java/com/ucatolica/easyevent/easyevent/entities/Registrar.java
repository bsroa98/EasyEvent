package com.ucatolica.easyevent.easyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "registrar")

public class Registrar implements Serializable{
    @Column(name = "pass")
    private String pass1;

    @JsonIgnore
    @OneToMany(mappedBy = "idusuario")
    private Set<Evento> eventos = new LinkedHashSet<>();

    public Registrar() {
    }

    public Registrar(Integer id, String nombre,  String correo, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "resgistar_id")
    private Integer id;
    @Column (name = "")
    private String nombre;
    private Integer nit;
    private String correo;
    @Column (name = "contraseña")
    private String pass;



    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreempresa) {
        this.nombre = nombre;
    }





    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
