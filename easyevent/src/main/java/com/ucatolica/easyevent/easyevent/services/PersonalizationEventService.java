package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.entities.PersonalizationEvent;
import com.ucatolica.easyevent.easyevent.entities.PersonalizationEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalizationEventService {
    private final PersonalizationEventRepository personalizationEventRepository;

    @Autowired
    public PersonalizationEventService(PersonalizationEventRepository personalizationEventRepository) {
        this.personalizationEventRepository = personalizationEventRepository;
    }



    public PersonalizationEvent customizeEvent(String date, String descripcion, String actividades, String ubicacion, String categoria, Integer capacidad, String comida) {
            PersonalizationEvent personalizationEvent = new PersonalizationEvent(date,descripcion,actividades,ubicacion,categoria,capacidad,comida);
            return personalizationEventRepository.save(personalizationEvent);
        }
}

