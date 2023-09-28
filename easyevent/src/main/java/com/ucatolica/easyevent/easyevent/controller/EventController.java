package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.EventoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("eventos")
public class EventController {

    public EventController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    private EventoRepository eventoRepository;


    @GetMapping("/GET")
    public List<Evento> getAll(){
       return eventoRepository.getAll();
    }

    @GetMapping("/GETBYID/{id}")
    public Optional<Evento> getEvento(@PathVariable int id){
        return eventoRepository.getEvento(id);
    }

    //@PostMapping("/SAVE")



}
