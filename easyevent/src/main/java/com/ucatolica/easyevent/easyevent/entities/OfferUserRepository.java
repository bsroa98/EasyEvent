package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OfferUserRepository {

    @Autowired
    private OfferUserRepository offerUserRepository;

    public List<offerUser> getAll(){
        return (List<offerUser>) offerUserRepository.getAll();
    }

    public Optional<offerUser> getOfferUser(String nombreContacto){
        return offerUserRepository.getOfferUser(nombreContacto);
    }

    public offerUser saveofferUser(offerUser offeruser){
        return offerUserRepository.saveofferUser(offeruser);
    }

}
