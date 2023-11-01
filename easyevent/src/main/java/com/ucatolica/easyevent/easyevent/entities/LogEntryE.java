package com.ucatolica.easyevent.easyevent.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LogEntryE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timestamp; // Puedes usar un formato de fecha y hora
    private String user; // Nombre del usuario o identificación
    private String event; // Evento (activo/inactivo)
    private String exceptionMessage; // Mensaje de excepción (si corresponde)
    private String ipAddress;

    public LogEntryE(String timestamp, String user, String event, String exceptionMessage, String ipAddress) {
        this.timestamp = timestamp;
        this.user = user;
        this.event = event;
        this.exceptionMessage = exceptionMessage;
        this.ipAddress = ipAddress;
    }

    public LogEntryE() {

    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
