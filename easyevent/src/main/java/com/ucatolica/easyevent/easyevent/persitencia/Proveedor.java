package com.ucatolica.easyevent.easyevent.persitencia;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "proveedores")

public class Proveedor implements Serializable {

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

    @Query(value = "SELECT * FROM proveedores", nativeQuery = true)
    List<Proveedor> findAllNative() {
        return null;
    }
}
