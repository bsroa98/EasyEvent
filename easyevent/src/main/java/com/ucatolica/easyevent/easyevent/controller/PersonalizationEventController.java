package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.PersonalizationEvent;
import com.ucatolica.easyevent.easyevent.services.PersonalizationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class PersonalizationEventController {
    private final PersonalizationEventService personalizationEventService;

    @Autowired
    public PersonalizationEventController(PersonalizationEventService personalizationEventService) {
        this.personalizationEventService = personalizationEventService;
    }

    @PutMapping ("/{eventId}")
    public PersonalizationEvent customizeEvent(@RequestParam String date, @RequestParam String descripcion,@RequestParam String actividades,@RequestParam String ubicacion,@RequestParam String categoria,@RequestParam Integer capacidad, @RequestParam String comida) {
        return personalizationEventService.customizeEvent(date,descripcion,actividades,ubicacion,categoria,capacidad,comida);
    }






}
