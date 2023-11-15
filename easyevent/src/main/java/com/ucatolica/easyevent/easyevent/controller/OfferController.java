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
import com.ucatolica.easyevent.easyevent.JWT.JwtUtil;

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
    public ResponseEntity<?> getOfferUser(@PathVariable String nombreContacto, @RequestHeader("Authorization") String authorization) {
        try {
            Optional<offerUser> ofertaGuardada = offerUserService.getOfferUserByName(nombreContacto);

            if (!JwtUtil.verifyToken(authorization)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return ResponseEntity.ok().body(ofertaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Autowired
    private LogService logService;
    @PostMapping("/saveOfferUser")
    public ResponseEntity<?> saveOfferUser(@RequestBody offerUser offeruser, @RequestBody Evento evento, @RequestBody HttpServletRequest request) {
        try {
            offerUser ofertaGuardada = offerUserService.saveOfferUser(offeruser);

            String token = JwtUtil.generateToken(offeruser.getNombreContacto());

            if (ofertaGuardada != null && evento != null){
                emailService.sendEmail(offeruser.getCorreoContacto(),"Guardado exitoso","Hola "+offeruser.getNombreContacto()+"; Tu oferta de empleo para el proveedor " +evento.getNombreEvento()+" ha sido guardado con exito");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            logService.logActivity("username", "Offer Saved", "No exception", getClientIp(request));

            return ResponseEntity.ok().body("Oferta creada\nToken: " + token);
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
