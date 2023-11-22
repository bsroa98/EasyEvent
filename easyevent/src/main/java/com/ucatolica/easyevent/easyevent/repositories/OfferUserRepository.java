package com.ucatolica.easyevent.easyevent.repositories;

import com.ucatolica.easyevent.easyevent.entities.offerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class OfferUserRepository implements Serializable{

    @Autowired
    private offerUserCrudRepository offerUserCrudRepository;

    public OfferUserRepository(com.ucatolica.easyevent.easyevent.repositories.offerUserCrudRepository offerUserCrudRepository) {
        this.offerUserCrudRepository = offerUserCrudRepository;
    }

    public List<offerUser> getAll(){
        return (List<offerUser>) offerUserCrudRepository.findAll();
    }

    public Optional<offerUser> getOfferUser(String nombreContacto){
        return offerUserCrudRepository.findById(Integer.valueOf(nombreContacto));
    }

    public offerUser saveOfferUser(offerUser offeruser){
        return offerUserCrudRepository.save(offeruser);
    }

}
