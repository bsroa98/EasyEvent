package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.ExceptionLog;
import com.ucatolica.easyevent.easyevent.entities.ExceptionLogRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExceptionLogService {
    @Autowired
    private ExceptionLogRespository exceptionLogRespository;

    public ExceptionLog logException(String objectName, String exceptionMessage,String ipAddress){
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setObjectName(objectName);
        exceptionLog.setExceptionMessage(exceptionMessage);
        exceptionLog.setIpAddress(ipAddress);
        exceptionLog.setTimestamp(LocalDateTime.now());

        return exceptionLogRespository.save(exceptionLog);

    }
}
