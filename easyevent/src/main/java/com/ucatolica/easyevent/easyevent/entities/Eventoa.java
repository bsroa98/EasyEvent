package com.ucatolica.easyevent.easyevent.entities;
import com.ucatolica.easyevent.easyevent.entities.Eventoa;
import java.util.HashMap;
import java.util.Map;


class Eventoa {
    private String name;
    private String date;
    private String location;
    private String menu;
    private String decoration;

    public Eventoa(String name, String date, String location, String menu, String decoration) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.menu = menu;
        this.decoration = decoration;
    }
    public void setCustomization(String field, String value) {
        // Implement validation if necessary
        switch (field) {
            case "date":
                this.date = value;
                break;
            case "location":
                this.location = value;
                break;
            case "menu":
                this.menu = value;
                break;
            case "decoration":
                this.decoration = value;
                break;

        }
    }
    public void resetToDefault() {
        // Reset event details to default values
    }
}

class EventQuoter {
    private Map<String, Eventoa> eventoa;

    public EventQuoter() {
        eventoa = new HashMap<>();
    }

    public void addEventoa(String name, String date, String location, String menu, String decoration) {
        Eventoa event = new Eventoa (name, date, location, menu, decoration);
        eventoa.put(name, event);
    }

    public Eventoa getEvent(String name) {
        return eventoa.get(name);
    }




    public static void main(String[] args) {
        EventQuoter quoter = new EventQuoter();

        // Agregar eventos
        quoter.addEventoa("Evento 1", "Fecha 1", "Ubicación 1", "Menú 1", "Decoración 1");
        quoter.addEventoa("Evento 2", "Fecha 2", "Ubicación 2", "Menú 2", "Decoración 2");

        // Personalizar un evento
        Eventoa eventoa = quoter.getEventoa("Evento 1");
        eventoa.setCustomization("date", "Nueva Fecha");
        eventoa.setCustomization("menu", "Nuevo Menú");

        // Restaurar valores predeterminados
        eventoa.resetToDefault();
    }

    private Eventoa getEventoa(String s) {

        return null;
    }
      }
