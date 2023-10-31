package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.PersonalizationEvent;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.PersonalizationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class PersonalizationEventController {
    public PersonalizationEventController(PersonalizationEventService personalizationEventService, PersonalizationEvent personalizationEvent, EmailService emailService) {
        this.personalizationEventService = personalizationEventService;
        this.personalizationEvent = personalizationEvent;
        this.emailService = emailService;
    }
    private PersonalizationEventService personalizationEventService;
    private PersonalizationEvent personalizationEvent;
    private EmailService emailService;

    @Autowired
    public PersonalizationEventController(PersonalizationEventService personalizationEventService) {
        this.personalizationEventService = personalizationEventService;
    }

    @PutMapping ("/{eventId}")
    public PersonalizationEvent customizeEvent(@RequestParam String date, @RequestParam String descripcion,@RequestParam String actividades,@RequestParam String ubicacion,@RequestParam String categoria,@RequestParam Integer capacidad, @RequestParam String comida) {
        return personalizationEventService.customizeEvent(date,descripcion,actividades,ubicacion,categoria,capacidad,comida);
    }

    @PostMapping("/updatePersEvent")
    public ResponseEntity<?> savePersonalizationEvent(@RequestBody PersonalizationEvent personalizationEvent, @RequestBody Evento evento) {
        try {
            if (personalizationEvent != null){
                emailService.sendEmail(evento.getEstado(),"Guardado exitoso","Hola para la fecha "+personalizationEvent.getDate()+"; Tu evento " +evento.getNombreEvento()+" ha sido personalizado con exito");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(personalizationEvent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






}
