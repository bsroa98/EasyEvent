package com.ucatolica.easyevent.easyevent.entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PersonalizationEventRepository extends JpaRepository<PersonalizationEvent, Long> {

    //En este caso realizará la busqueda de todos los eventos que tengan una ubicación específica.
    List<PersonalizationEvent> findByLocation(String ubicacion);

    // En este caso realizará la busqueda de los eventos cuya fecha sea igual a la fecha proporcionada.
    @Query("SELECT e FROM eventos e WHERE e.date >= :startDate")
    List<PersonalizationEvent> findEventsAfterDate(@Param("startDate") Date startDate);

    //En este caso realizará la busqueda de eventos en una ubicación específica con una fecha igual o posterior a la fecha que sea proporcionada.
    @Query("SELECT e FROM eventos e WHERE e.ubicacion = :ubicacion AND e.date >= :startDate")
    List<PersonalizationEvent> findEventsByLocationAndDateAfter(@Param("ubicacion") String ubicacion, @Param("startDate") Date startDate);

    // En este caso se ejecutará una consulta SQL nativa que buscará eventos por ubicación.
    @Query(value = "SELECT * FROM eventos WHERE ubicacion = :ubicacion", nativeQuery = true)
    List<PersonalizationEvent> findEventsByLocation(@Param("ubicacion") String ubicacion);


}
