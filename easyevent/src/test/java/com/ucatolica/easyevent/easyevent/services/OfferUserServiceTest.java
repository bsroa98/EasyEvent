package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.model.offerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OfferUserServiceTest {

    @Autowired
    private OfferUserService offerUserService;

    @Test
    public void getAllOffers() {
        List<offerUser> offers = offerUserService.getAllOffers();
        assertTrue(offers.size() > 0);
    }

    @Test
    public void getOfferUserByName() {
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto");
        offerUser savedOffer = offerUserService.saveOfferUser(offer);
        Optional<offerUser> retrievedOffer = offerUserService.getOfferUserByName(savedOffer.getNombreContacto());
        assertTrue(retrievedOffer.isPresent());
        assertEquals(savedOffer, retrievedOffer.get());
    }

    @Test
    public void saveOfferUser() {
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto");
        offerUser savedOffer = offerUserService.saveOfferUser(offer);
        assertNotNull(savedOffer.getNombreContacto());
        assertEquals(offer.getNombreContacto(), savedOffer.getNombreContacto());
        assertEquals(offer.getCorreoContacto(), savedOffer.getCorreoContacto());
    }
}