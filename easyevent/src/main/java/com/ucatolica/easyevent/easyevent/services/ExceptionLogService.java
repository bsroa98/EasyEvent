package com.ucatolica.easyevent.easyevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExceptionLogService {


    public String logException(String objectName, String exceptionMessage,String ipAddress, String timeStamp){

        return "[" + timeStamp + "] " +
                "Object Name: " + objectName + ", " +
                "Exception Message: " + exceptionMessage + ", " +
                "IP Address: " + ipAddress + "\n";


    }
}
