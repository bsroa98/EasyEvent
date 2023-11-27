package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.repositories.EventoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventoRepository eventoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        Evento evento1 = new Evento("test","evento1");
        Evento evento2 = new Evento("test2","evento2");
        List<Evento> eventos= Arrays.asList(evento1,evento2);

        when(eventoRepository.getAll()).thenReturn(eventos);
        List<Evento> result=eventService.getAllEvents();

        Assertions.assertEquals(2,result.size());
    }

    @Test
    void testSaveEvento() {
        // Configurar el evento de prueba
        Evento evento = new Evento("test","evento");
        evento.setPrecio(BigDecimal.TEN); // Precio válido
        evento.setCapacidad(50); // Capacidad válida

        // Simular el comportamiento del repositorio al guardar el evento
        when(eventoRepository.save(evento)).thenReturn(evento);

        // Llamar al método saveEvento del servicio
        ResponseEntity<Evento> response = eventService.saveEvento(evento);

        // Verificacion
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Verificar el código de estado
        assertEquals(evento, response.getBody()); // Verificar que el evento devuelto sea el mismo que se guardó
    }

    @Test
    void testDeleteEvento() {
        // Configurar el evento de prueba
        Evento evento = new Evento(/* Datos del evento */);

        // Llamar al método deleteEvento del servicio
        eventService.deleteEvento(evento);

        // Verificar que se llamó al método delete del repositorio con el evento proporcionado
        verify(eventoRepository, times(1)).delete(evento);
    }

    @Test
    void testDeleteEventoById() {
        // Configurar un ID de evento de prueba
        Integer eventId = 1;

        // Configurar el evento de prueba que debería devolverse al obtenerlo por su ID
        Evento evento = new Evento(/* Datos del evento */);

        // Configurar el comportamiento simulado del repositorio al obtener el evento por su ID
        when(eventoRepository.getEvento(eventId)).thenReturn(Optional.of(evento));

        // Llamar al método deleteEventoById del servicio
        boolean result = eventService.deleteEventoById(eventId);

        // Verificar que se llamó al método delete del repositorio con el evento obtenido por su ID
        verify(eventoRepository, times(1)).delete(evento);
        // Verificar que el método devuelve true ya que se pudo eliminar el evento
        assertTrue(result);
    }

    @Test
    void testDeleteEventoById_EventoNoEncontrado() {
        // Configurar un ID de evento de prueba
        Integer eventId = 1;

        // Configurar el comportamiento simulado del repositorio al no obtener el evento por su ID
        when(eventoRepository.getEvento(eventId)).thenReturn(Optional.empty());

        // Llamar al método deleteEventoById del servicio
        boolean result = eventService.deleteEventoById(eventId);

        // Verificar que el método devuelve false ya que el evento no fue encontrado
        assertFalse(result);
        // Verificar que el método delete del repositorio no se llamó porque el evento no existía
        verify(eventoRepository, never()).delete(any());
    }

}
