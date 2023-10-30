package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.Proveedor;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {


    public EventController(EventService eventService, EmailService emailService,ProveedorService proveedorService) {
        this.eventService = eventService;
        this.emailService = emailService;
        this.proveedorService = proveedorService;
    }

    private EventService eventService;
    private EmailService emailService;

    private ProveedorService proveedorService;


    @GetMapping("/eventos")
    public List<Evento> getAll(){

        return eventService.getAllEvents();
    }

    @GetMapping("/eventos/{id}")
    public Optional<Evento> getEvento(@PathVariable int id){
        return eventService.getEventoById(id);
    }

    @PostMapping("/eventos/save")
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento) {
        try {
            ResponseEntity<Evento> eventoGuardado = eventService.saveEvento(evento);
            Proveedor proveedorTemp = evento.getIdproveedor();
            Optional<Proveedor> optionalProveedor= proveedorService.getProveedorById(proveedorTemp.getId());

            if (optionalProveedor.isPresent()){
            Proveedor proveedor = optionalProveedor.get();
            emailService.sendEmail(proveedor.getCorreo(),"Guardado exitoso","Hola "+proveedor.getNombreempresa()+"; Tu evento " +evento.getNombreEvento()+" ha sido guardado con exito");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eventos/delete")
    public void deleteEvento(@RequestBody Evento evento){
        eventService.deleteEvento(evento);
    }



}
