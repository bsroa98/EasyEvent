package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.OfferUserRepository;
import com.ucatolica.easyevent.easyevent.entities.offerUser;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ofertaUsuario")
public class OfferController {

    private final OfferUserRepository offerUserRepository;

    @Autowired
    public OfferController(OfferUserRepository offerUserRepository) {
        this.offerUserRepository = offerUserRepository;
    }

    @GetMapping("/GET")
    public List<offerUser> getAll(){
        return offerUserRepository.getAll();
    }

    @GetMapping("/GETBYNAME/{nombreContacto}")
    public Optional<offerUser> getOfferUser(@PathVariable String nombreContacto){
        return offerUserRepository.getOfferUser(nombreContacto);
    }

    @PostMapping("/saveOfferUser")
    public offerUser saveOfferUser(@RequestBody offerUser offerUser) {
        return offerUserRepository.saveofferUser(offerUser);
    }
}
