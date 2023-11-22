package com.ucatolica.easyevent.easyevent.services;
import com.ucatolica.easyevent.easyevent.repositories.LogEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ucatolica.easyevent.easyevent.entities.LogEntity;

import java.time.LocalDate;

@Service
public class LogService {

    private final LogEntityRepository logEntityRepository;

    @Autowired
    public LogService(LogEntityRepository logEntityRepository) {
        this.logEntityRepository = logEntityRepository;
    }
    public void logActivity(String username, String status, String exceptionMessage, String ipAddress) {
        LogEntity logEntity = new LogEntity();
        logEntity.setDate(LocalDate.now());
        logEntity.setUsername(username);
        logEntity.setStatus(status);
        logEntity.setExceptionMessage(exceptionMessage);
        logEntity.setIpAddress(ipAddress);

        logEntityRepository.save(logEntity);
    }
}