package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.offerUser;
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
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto",321,"mesero");
        offerUser savedOffer = offerUserService.saveOfferUser(offer);
        offerUser offer2 = new offerUser("Nombre Contacto2", "Correo Contacto",321,"mesero");
        //Optional<offerUser> retrievedOffer = offerUserService.getOfferUserByName(savedOffer.getNombreContacto());
        //assertTrue(retrievedOffer.isPresent());
        assertEquals(offer2, savedOffer);
    }

    @Test
    public void saveOfferUser() {
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto",321,"mesero");
        offerUser savedOffer = offerUserService.saveOfferUser(offer);
        assertNotNull(savedOffer.getNombreContacto());
        assertEquals(offer, savedOffer);
    }

}