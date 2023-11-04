package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.LogEntryE;
import com.ucatolica.easyevent.easyevent.entities.LogRepositoryE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {
    private final LogRepositoryE logRepositoryE; // Puedes crear un repositorio si estás usando una base de datos

    @Autowired
    public LogService(LogRepositoryE logRepositoryE) {
        this.logRepositoryE = logRepositoryE;
    }

    public void logEvent(String user, String event, String exceptionMessage, String ipAddress) {
        LogEntryE logEntryE =   new LogEntryE();
        logEntryE.setTimestamp(LocalDateTime.now().toString()); // Puedes ajustar el formato según tus necesidades
        logEntryE.setUser(user);
        logEntryE.setEvent(event);
        logEntryE.setExceptionMessage(exceptionMessage);
        logEntryE.setIpAddress(ipAddress);

        // Guarda el registro en la ubicación deseada, como una base de datos o un archivo de registro
        logRepositoryE.save(logEntryE); // Si usas una base de datos

    }
}
