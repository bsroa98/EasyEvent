package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.*;

@Service
public class EventService {
    private EventoRepository eventoRepository;

    public EventService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getAllEvents() {
        return eventoRepository.getAll();
    }

    public Optional<Evento> getEventoById(int id) {
        return eventoRepository.getEvento(id);
    }

    public Evento saveEvento(Evento evento){
        if (evento.getPrecio().compareTo(ZERO)<0){
            evento.setPrecio(ZERO);
        }
        if (evento.getCapacidad()<0){
            evento.setCapacidad(0);

        }
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Evento evento){
        eventoRepository.delete(evento);
    }
}
