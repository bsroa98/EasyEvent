package com.ucatolica.easyevent.easyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "proveedores")

public class Proveedor implements Serializable {

    @Column(name = "pass")
    private String pass1;

    @JsonIgnore
    @OneToMany(mappedBy = "idproveedor")
    private Set<Evento> eventos = new LinkedHashSet<>();

    public Proveedor() {
    }

    public Proveedor(Integer id, String nombreempresa, Integer nit, String correo, String pass) {
        this.id = id;
        this.nombreempresa = nombreempresa;
        this.nit = nit;
        this.correo = correo;
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "proveedor_id")
    private Integer id;
    @Column (name = "nombre_empresa")
    private String nombreempresa;
    private Integer nit;
    private String correo;
    @Column (name = "contraseña")
    private String pass;

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

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

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
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
/*
    TODO [JPA Buddy] create field to map the 'rol' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "rol", columnDefinition = "rol_enum(0, 0)")
    private java.lang.Object rol;
*/
}
