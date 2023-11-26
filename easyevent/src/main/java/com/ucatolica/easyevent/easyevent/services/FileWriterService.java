package com.ucatolica.easyevent.easyevent.services;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileWriterService {

    public ResponseEntity<String> writeFile(String fileName, String logText) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(logText);
            return ResponseEntity.status(HttpStatus.CREATED).body("Archivo creado con exito");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
}