package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.EventoCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    public EventController(EventoCrudRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    private EventoCrudRepository eventoRepository;


    @GetMapping("/eventos")
    public List<Evento> getAll(){
        List<Evento> list = new ArrayList();
        Iterator<Evento> iterator = eventoRepository.findAll().iterator();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    @GetMapping("/nya")
    public Optional<Evento> nya(){
        return eventoRepository.findById(10);
    }
}
