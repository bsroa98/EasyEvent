package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.model.offerUser;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.LogService;
import com.ucatolica.easyevent.easyevent.services.OfferUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OfferControllerTest {

    @Autowired
    private OfferController offerController;

    @Autowired
    private OfferUserService offerUserService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LogService logService;

    @Test
    public void getAll() {
        List<offerUser> offers = offerController.getAll();
        assertTrue(offers.size() > 0);
    }

    @Test
    public void getOfferUser() {
        offerUser offer = offerUserService.saveOfferUser(new offerUser("Nombre Contacto", "Correo Contacto"));
        ResponseEntity<?> response = offerController.getOfferUser(offer.getNombreContacto(), "Authorization");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void saveOfferUser() {
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto");
        Evento evento = new Evento("Nombre Evento", "Descripcion Evento");
        HttpServletRequest request = mock(HttpServletRequest.class);
        ResponseEntity<?> response = offerController.saveOfferUser(offer, evento, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getClientIp() {
        String ipAddress = offerController.getClientIp(mock(HttpServletRequest.class));
        assertTrue(ipAddress.length() > 0);
    }
}