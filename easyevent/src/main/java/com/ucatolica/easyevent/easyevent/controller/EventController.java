package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.model.Evento;
import com.ucatolica.easyevent.easyevent.model.EventoDTO;

import com.ucatolica.easyevent.easyevent.model.Proveedor;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ucatolica.easyevent.easyevent.repository.ProveedorRepository;
//import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/evento")
    public class EventController {



        private EventService eventService;
        private EmailService emailService;

        private ProveedorService proveedorService;
        private ProveedorRepository proveedorRepository;


        @GetMapping("/eventos")
        public List<Evento> getAll(){

            return eventService.getAllEvents();
        }

        @GetMapping("/eventos/{id}")
        public Optional<Evento> getEvento(@PathVariable int id){
            return eventService.getEventoById(id);
        }

        @PostMapping("/eventos/save")
        public ResponseEntity<String> crearEvento( @RequestBody EventoDTO eventoDTO) {
            try {
                Evento evento = eventoDTO.getEvento();
                ResponseEntity<Evento> eventoGuardado = eventService.saveEvento(evento);


                Optional<Proveedor> optionalProveedor= proveedorService.getProveedorById(eventoDTO.getIdproveedor());

                if (optionalProveedor.isPresent()){
                Proveedor proveedor = optionalProveedor.get();
                emailService.sendEmail(proveedor.getCorreo(),"Guardado exitoso","Hola "+proveedor.getNombreempresa()+"; Tu evento " +evento.getNombreEvento()+" ha sido guardado con exito");}
                else{
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
                return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @DeleteMapping("/eventos/delete")
        public void deleteEvento(@RequestBody Evento evento){
            eventService.deleteEvento(evento);
        }



    }
