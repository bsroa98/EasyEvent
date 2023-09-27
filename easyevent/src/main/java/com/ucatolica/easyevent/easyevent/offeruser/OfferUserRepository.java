package com.ucatolica.easyevent.easyevent.offeruser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OfferUserRepository {

    @Autowired
    private OfferUserRepository offerUserRepository;

    public List<offerUser> getAll(){
        return (List<offerUser>) offerUserRepository.findAll();
    }

    public Optional<offerUser> getofferUser(String nombreContacto){
        return offerUserRepository.findById(nombreContacto);
    }

    public offerUser saveofferUser(offerUser offeruser){
        return offerUserRepository.save(offeruser);
    }
    
    public void deleteofferUser(offerUser offeruser){
        offerUserRepository.delete(offeruser);
    }

}
