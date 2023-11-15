package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.model.offerUser;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.LogService;
import com.ucatolica.easyevent.easyevent.services.OfferUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LogService logService;
    @PostMapping("/saveOfferUser")
    public ResponseEntity<?> saveOfferUser(@RequestBody offerUser offeruser, @RequestBody Evento evento, @RequestBody HttpServletRequest request) {
        try {
            offerUser ofertaGuardada = offerUserService.saveOfferUser(offeruser);

            if (ofertaGuardada != null && evento != null){
                emailService.sendEmail(offeruser.getCorreoContacto(),"Guardado exitoso","Hola "+offeruser.getNombreContacto()+"; Tu oferta de empleo para el proveedor " +evento.getNombreEvento()+" ha sido guardado con exito");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            logService.logActivity("username", "Offer Saved", "No exception", getClientIp(request));

            return ResponseEntity.status(HttpStatus.CREATED).body(ofertaGuardada);
        } catch (Exception e) {
            logService.logActivity("username", "Offer Save Failed", e.getMessage(), getClientIp(request));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
