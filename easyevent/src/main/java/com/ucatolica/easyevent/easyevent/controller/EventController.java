package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.Proveedor;
import com.ucatolica.easyevent.easyevent.entities.EventoRepository;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class EventController {


    public EventController(EventService eventService, EmailService emailService) {
        this.eventService = eventService;
        this.emailService = emailService;
    }

    private EmailService emailService;
    private EventService eventService;
    private Proveedor proveedor;

    @GetMapping("/eventos")
    public List<Evento> getAll(){

        return eventService.getAllEvents();
    }

    @GetMapping("/eventos/{id}")
    public Optional<Evento> getEvento(@PathVariable int id){
        return eventService.getEventoById(id);
    }

    @PostMapping("/eventos/save")
    public Evento saveEvento(@RequestBody Evento evento){
        emailService.sendEmail(proveedor.getCorreo(),"Evento Guardado con exito", "Señor(es) " + proveedor.getNombreempresa()+"Su evento ha sido guardado con exito");
        return eventService.saveEvento(evento);
    }

    @DeleteMapping("/eventos/delete")
    public void deleteEvento(@RequestBody Evento evento){
        eventService.deleteEvento(evento);
    }



}
