package com.ucatolica.easyevent.easyevent.entities;

class EventoManager {
    private List<Eventoa> eventoa;

    public EventoManager() {eventoa = new ArrayList<>();
    }

    public void addEventoa(Eventoa eventoa) {
        eventoa.add(eventoa);
    }

    public void updateEventoa(Eventoa eventoa, String field, String value) {
        // Implementar la actualización de eventos
    }

    public void deleteEventoa(Eventoa event) {
        eventoa.remove(eventoa);
    }

    public List<Eventoa> getEventoaSortedByDate() {
        // Implementar la ordenación de eventos por fecha
        return eventoa;
    }
}

public class AdminEventoaViewer {
    public static void main(String[] args) {
        // Crear una lista de eventos y agregar eventos
        EventoManager eventManager = new EventoManager();
        Eventoa event1 = new Eventoa("Evento 1", "Fecha 1", "Ubicación 1", "Menú 1", "Decoración 1");
        Eventoa event2 = new Eventoa("Evento 2", "Fecha 2", "Ubicación 2", "Menú 2", "Decoración 2");
        eventoManager.addEventoa(event1);
        eventoManager.addEventoa(event2);

        // Mostrar la lista de eventos ordenados por fecha
        List<Eventoa> eventoa = eventoManager.getEventoaSortedByDate();
        for (Eventoa eventoa : eventoa) {
            // Mostrar detalles del evento
            System.out.println("Fecha: " + eventoa.getDate());
            System.out.println("Ubicación: " + eventoa.getLocation());
            // ... Mostrar otros detalles del evento ...
        }

        // Actualizar un evento
        eventoManager.updateEvent(event1, "date", "Nueva Fecha");

        // Eliminar un evento
        eventoManager.deleteEvento(event2);
    }
}
