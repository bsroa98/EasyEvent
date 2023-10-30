package com.ucatolica.easyevent.easyevent.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class MenuEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String entrada;
    private String platoFuerte;
    private String postre;
    private String bebida;
    private double precio;

    public MenuEvent(String name, String entrada, String platoFuerte, String postre, String bebida, double precio) {
        this.name = name;
        this.entrada = entrada;
        this.platoFuerte = platoFuerte;
        this.postre = postre;
        this.bebida = bebida;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getPlatoFuerte() {
        return platoFuerte;
    }

    public void setPlatoFuerte(String platoFuerte) {
        this.platoFuerte = platoFuerte;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}