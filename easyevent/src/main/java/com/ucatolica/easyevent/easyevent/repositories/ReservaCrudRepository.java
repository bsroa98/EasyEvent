package com.ucatolica.easyevent.easyevent.repositories;

import com.ucatolica.easyevent.easyevent.entities.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface ReservaCrudRepository extends CrudRepository<Reserva,Integer> {
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.fechaEvento = :fechaevento")
    int countFechas(@Param("fechaevento") LocalDateTime fechaevento);
}
