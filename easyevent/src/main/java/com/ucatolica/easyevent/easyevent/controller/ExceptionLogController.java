package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ExceptionLogService;
import com.ucatolica.easyevent.easyevent.services.FileWriterService;
import com.ucatolica.easyevent.easyevent.services.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/log")
public class ExceptionLogController {

    @Autowired
    private ExceptionLogService exceptionLogService;

    private IpAddressService ipAddressService;
    private EventService eventService;
    private FileWriterService fileWriterService;

    public ExceptionLogController(ExceptionLogService exceptionLogService, IpAddressService ipAddressService, EventService eventService, FileWriterService fileWriterService) {
        this.exceptionLogService = exceptionLogService;
        this.ipAddressService = ipAddressService;
        this.eventService = eventService;
        this.fileWriterService = fileWriterService;
    }

    @GetMapping("/eventoslog/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Evento> getEvento(@PathVariable int id){
        try {
           {
                Optional<Evento> evento = eventService.getEventoById(id);
                if (evento.isEmpty()){
                    throw new IllegalArgumentException("Id erroneo");

                }
                return evento;
            }
        }
        catch (Exception e){
            //String objectName = "Sin Stack Trace";
            String objectName = e.getStackTrace().toString();
            String exceptionMessage = e.getMessage();
            String ipAddress = ipAddressService.GetIp();
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String date = currentDateTime.format(formatter);
            String logText = exceptionLogService.logException(objectName,exceptionMessage,ipAddress,date);
            fileWriterService.writeFile("ExceptionLog.txt", logText);
            return Optional.empty();
        }
    }
}
