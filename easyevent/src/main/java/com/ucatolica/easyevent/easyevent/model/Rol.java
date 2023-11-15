package com.ucatolica.easyevent.easyevent.model;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Erol name;

    public Rol() {

    }

    public Rol(Erol name) {
        this.name = name;
    }

    public Erol getName() {
        return name;
    }

    public void setName(Erol name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    // getters and setters
}