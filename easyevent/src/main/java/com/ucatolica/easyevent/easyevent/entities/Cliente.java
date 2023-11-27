package com.ucatolica.easyevent.easyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clientes",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "correo")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteid")
    private Set<Reserva> reservas = new LinkedHashSet<>();

    @Column(name = "nombre", length = 100)
    private String nombre;


    @NotBlank
    @Column(name = "correo", length = 100)
    private String correo;

    @NotBlank
    @Column(name = "pass", length = 100)
    private String pass;

    @Column(name = "numero_identificacion", length = 20)
    private String numeroIdentificacion;

    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    private String username;

    @Column(name = "Verificado")
    private Boolean Verificado = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean isVerificado() {
        return Verificado;
    }

    public void setVerificado() {
        this.Verificado = true;
    }

    public Cliente(String nombre, String correo, String pass, String numeroIdentificacion, String direccion, String username) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.numeroIdentificacion = numeroIdentificacion;
        this.direccion = direccion;
        this.username = username;
    }

    public Cliente() {
    }


}