package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.ExceptionLog;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ExceptionLogService;
import com.ucatolica.easyevent.easyevent.services.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ExceptionLogController {
    @Autowired
    private ExceptionLogService exceptionLogService;

    private IpAddressService ipAddressService;
    private EventService eventService;

    public ExceptionLogController(ExceptionLogService exceptionLogService, IpAddressService ipAddressService, EventService eventService) {
        this.exceptionLogService = exceptionLogService;
        this.ipAddressService = ipAddressService;
        this.eventService = eventService;
    }

    @GetMapping("/eventoslog/{id}")
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
            String objectName = "Sin Stack Trace";
            if (e.getCause() !=null){
                objectName = e.getStackTrace().toString();
            }
            String exceptionMessage = e.getMessage();
            String ipAddress = ipAddressService.GetIp();
            ExceptionLog log = exceptionLogService.logException(objectName, exceptionMessage, ipAddress);
            return Optional.empty();
        }
    }
}
