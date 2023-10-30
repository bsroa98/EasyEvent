package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.offerUser;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.OfferUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    public OfferController(OfferUserService offerUserService, EmailService emailService) {
        this.offerUserService = offerUserService;
        this.emailService = emailService;
    }

    private OfferUserService offerUserService;
    private EmailService emailService;


    @GetMapping("/getOffers")
    public List<offerUser> getAll(){
        return offerUserService.getAllOffers();
    }

    @GetMapping("/GETBYNAME/{nombreContacto}")
    public Optional<offerUser> getOfferUser(@PathVariable String nombreContacto){
        return offerUserService.getOfferUserByName(nombreContacto);
    }

    @PostMapping("/saveOfferUser")
    public ResponseEntity<?> saveOfferUser(@RequestBody offerUser offeruser, @RequestBody Evento evento) {
        try {
            offerUser ofertaGuardada = offerUserService.saveOfferUser(offeruser);

            if (ofertaGuardada != null){
                emailService.sendEmail(offeruser.getCorreoContacto(),"Guardado exitoso","Hola "+offeruser.getNombreContacto()+"; Tu oferta de empleo para el proveedor " +evento.getNombreEvento()+" ha sido guardado con exito");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(ofertaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
