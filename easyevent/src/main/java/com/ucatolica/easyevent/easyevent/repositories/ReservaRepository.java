package com.ucatolica.easyevent.easyevent.repositories;

import com.ucatolica.easyevent.easyevent.entities.Reserva;
import com.ucatolica.easyevent.easyevent.entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository implements Serializable {
    @Autowired
    private ReservaCrudRepository reservaCrudRepository;

    public List<Reserva> getAll(){return (List<Reserva>) reservaCrudRepository.findAll();}
    public Optional<Reserva> getReserva(int id){return reservaCrudRepository.findById(id);}

    public Reserva save(Reserva reserva){return reservaCrudRepository.save(reserva);}

    public void delete(Reserva reserva){reservaCrudRepository.delete(reserva);}
}
