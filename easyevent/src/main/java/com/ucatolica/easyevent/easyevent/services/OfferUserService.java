package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.repositories.OfferUserRepository;
import com.ucatolica.easyevent.easyevent.model.offerUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
