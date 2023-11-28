package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.ExceptionHandler.BadRequestException;
import com.ucatolica.easyevent.easyevent.entities.Cliente;
import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.Reserva;
import com.ucatolica.easyevent.easyevent.repositories.EventoRepository;
import com.ucatolica.easyevent.easyevent.repositories.ReservaCrudRepository;
import com.ucatolica.easyevent.easyevent.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final EventService eventService;
    private final ClientService clientService;
    private final ReservaCrudRepository reservaCrudRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, EventoRepository eventoRepository, EventService eventService, ClientService clientService, ReservaCrudRepository reservaCrudRepository) {
        this.reservaRepository = reservaRepository;
        this.eventService = eventService;
        this.clientService = clientService;
        this.reservaCrudRepository = reservaCrudRepository;
    }

    public List<Reserva> getAllReserva(){return reservaRepository.getAll();}

    public Optional<Reserva> getReservaById(int id) {
             return reservaRepository.getReserva(id);

    }

    public ResponseEntity<?> saveReserva(Reserva reserva) {
        Evento eventoid = reserva.getEventoid();
        if (eventoid==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id Nulo");
        }
        if (reserva.getEventoid().getId().toString().length()>10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IdErroneo");
        }
        Optional<Evento> optionalEvento=eventService.getEventoById(eventoid.getId());
        Cliente clienteid = reserva.getClienteid();
        Optional<Cliente> optionalCliente=clientService.getClienteById(clienteid.getId());
        LocalDateTime fechaEvento = reserva.getFechaEvento();
        LocalDateTime fechaReserva = LocalDateTime.now();
        reserva.setFechaReserva(fechaReserva);
        int abonoR = reserva.getAbono();
        int precioT = reserva.getPrecioTotal();
        double abonoMin = precioT*0.5;
        int countReserva = reservaCrudRepository.countFechas(fechaEvento);
        System.out.println(countReserva);
        if(optionalCliente.isPresent() && optionalEvento.isPresent()){
            Evento evento = optionalEvento.get();
            Cliente cliente = optionalCliente.get();
            if(fechaEvento.isBefore(fechaReserva)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("errorFecha");
            }
            if(!cliente.isVerificado()){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("errorVerificacion");
            }
            if(countReserva>0){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("errorDisp");
            }
            if(evento.getCapacidad()>50){
                if(abonoR < abonoMin) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("errorAbono");
                }
            }

        }
        Reserva savedReserva = reservaRepository.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReserva);

    }

    public void deleteReserva(Reserva reserva){
        reservaRepository.delete(reserva);
    }

    public boolean deleteReservaById(Integer id) {
        Optional<Reserva> optionalReserva = reservaRepository.getReserva(id);

        if (optionalReserva.isPresent()) {
            reservaRepository.delete(optionalReserva.get());
            return true;
        } else {
            return false;
        }
    }
}
