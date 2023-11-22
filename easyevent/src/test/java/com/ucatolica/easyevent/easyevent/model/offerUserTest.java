package com.ucatolica.easyevent.easyevent.model;

import com.ucatolica.easyevent.easyevent.entities.offerUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class offerUserTest {

    @Test
    public void constructorWithAllParameters() {
        offerUser offerUser = new offerUser("Nombre Contacto", "Correo Contacto", 1234567890, "Experiencia");
        assertEquals("Nombre Contacto", offerUser.getNombreContacto());
        assertEquals("Correo Contacto", offerUser.getCorreoContacto());
        assertEquals(1234567890, offerUser.getTelefonoContacto());
        assertEquals("Experiencia", offerUser.getExperiencia());
    }

    @Test
    public void constructorWithTwoParameters() {
        offerUser offerUser = new offerUser("Nombre Contacto", "Correo Contacto");
        assertEquals("Nombre Contacto", offerUser.getNombreContacto());
        assertEquals("Correo Contacto", offerUser.getCorreoContacto());
        assertEquals(0, offerUser.getTelefonoContacto());
        assertNull(offerUser.getExperiencia());
    }

    @Test
    public void getNombreContacto() {
        offerUser offerUser = new offerUser("Nombre Contacto", "Correo Contacto", 1234567890, "Experiencia");
        assertEquals("Nombre Contacto", offerUser.getNombreContacto());
    }

    @Test
    public void setNombreContacto() {
        offerUser offerUser = new offerUser();
        offerUser.setNombreContacto("Nuevo Nombre Contacto");
        assertEquals("Nuevo Nombre Contacto", offerUser.getNombreContacto());
    }

    @Test
    public void getCorreoContacto() {
        offerUser offerUser = new offerUser("Nombre Contacto", "Correo Contacto", 1234567890, "Experiencia");
        assertEquals("Correo Contacto", offerUser.getCorreoContacto());
    }

    @Test
    public void setCorreoContacto() {
        offerUser offerUser = new offerUser();
        offerUser.setCorreoContacto("Nuevo Correo Contacto");
        assertEquals("Nuevo Correo Contacto", offerUser.getCorreoContacto());
    }

    @Test
    public void getTelefonoContacto() {
        offerUser offerUser = new offerUser("Nombre Contacto", "Correo Contacto", 1234567890, "Experiencia");
        assertEquals(1234567890, offerUser.getTelefonoContacto());
    }

    @Test
    public void setTelefonoContacto() {
        offerUser offerUser = new offerUser();
        offerUser.setTelefonoContacto(12345);
        assertEquals(12345, offerUser.getTelefonoContacto());
    }

    @Test
    public void getExperiencia() {
        offerUser offerUser = new offerUser("Nombre Contacto", "Correo Contacto", 1234567890, "Experiencia");
        assertEquals("Experiencia", offerUser.getExperiencia());
    }

    @Test
    public void setExperiencia() {
        offerUser offerUser = new offerUser();
        offerUser.setExperiencia("Nueva Experiencia");
        assertEquals("Nueva Experiencia", offerUser.getExperiencia());
    }
}