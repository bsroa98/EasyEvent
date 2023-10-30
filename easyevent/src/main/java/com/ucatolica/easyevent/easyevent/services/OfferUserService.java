package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.OfferUserRepository;
import com.ucatolica.easyevent.easyevent.entities.offerUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Service
public class OfferUserService {
    private OfferUserRepository offerUserRepository;

    public OfferUserService(OfferUserRepository offerUserRepository) {
        this.offerUserRepository = offerUserRepository;
    }

    public List<offerUser> getAllOffers() {
        return offerUserRepository.getAll();
    }

    public Optional<offerUser> getOfferUserByName(String nombreContacto) {
        return offerUserRepository.getOfferUser(nombreContacto);
    }

    public offerUser saveOfferUser(offerUser offeruser) {
        return offerUserRepository.saveOfferUser(offeruser);
    }

}
