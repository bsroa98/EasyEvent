package com.ucatolica.easyevent.easyevent.repositories;

import com.ucatolica.easyevent.easyevent.entities.offerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OfferClienteRepositoryTest {

    @Autowired
    private OfferUserRepository offerUserRepository;

    @Test
    public void getAll() {
        List<offerUser> offers = offerUserRepository.getAll();
        assertTrue(offers.size() > 0);
    }

    @Test
    public void getOfferUser() {
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto");
        offerUser savedOffer = offerUserRepository.saveOfferUser(offer);
        Optional<offerUser> retrievedOffer = offerUserRepository.getOfferUser(savedOffer.getNombreContacto());
        assertTrue(retrievedOffer.isPresent());
        assertEquals(savedOffer, retrievedOffer.get());
    }

    @Test
    public void saveOfferUser() {
        offerUser offer = new offerUser("Nombre Contacto", "Correo Contacto");
        offerUser savedOffer = offerUserRepository.saveOfferUser(offer);
        assertNotNull(savedOffer.getNombreContacto());
        assertEquals(offer.getNombreContacto(), savedOffer.getNombreContacto());
        assertEquals(offer.getCorreoContacto(), savedOffer.getCorreoContacto());
    }
}
