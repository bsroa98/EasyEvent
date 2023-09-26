package com.ucatolica.easyevent.easyevent.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "proveedores")

public class Proveedor implements Serializable {

    public Proveedor() {
    }

    public Proveedor(Integer id, String nombreEmpresa, Integer nit, String correo, String pass) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.nit = nit;
        this.correo = correo;
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "proveedor_id")
    private Integer id;
    @Column (name = "nombre_empresa")
    private String nombreEmpresa;
    private Integer nit;
    private String correo;
    @Column (name = "contraseña")
    private String pass;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreempresa) {
        this.nombreEmpresa = nombreEmpresa;
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
}
