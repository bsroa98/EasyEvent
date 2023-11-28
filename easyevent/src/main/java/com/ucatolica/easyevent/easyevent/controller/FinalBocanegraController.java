package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.FinalBocanegra;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.LogService;
import com.ucatolica.easyevent.easyevent.services.FinalBocanegraService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ucatolica.easyevent.easyevent.JWT.JwtUtil;

import java.util.List;
import java.util.Optional;

@Tag(name="FinalBocanegra")
@RestController
public class FinalBocanegraController {

    public FinalBocanegraController(FinalBocanegraService finalBocanegraService, EmailService emailService) {
        this.finalBocanegraService = finalBocanegraService;
        this.emailService = emailService;
    }

    private FinalBocanegraService finalBocanegraService;
    private EmailService emailService;

    @PostMapping("/SaveFinalBocanegra")
    public ResponseEntity<?> SaveFinalBocanegra(@RequestBody finalBocanegra finalbocanegra) {
        try {
            finalBocanegra Guardado = finalBocanegraService.SaveFinalBocanegra(finalbocanegra);
            for(int peti = 1; peti == 1; peti ++){
                if (Guardado != null){
                emailService.sendTextEmail(
                        finalbocanegra.getCorreoContacto(),
                        "Guardado exitoso",
                        "Hola "+finalbocanegra.getNombreContacto()+"; Ha sido guardado con exito FinalBocanegra");
                }else{
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
            return ResponseEntity.ok().body("No permite entrar");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}