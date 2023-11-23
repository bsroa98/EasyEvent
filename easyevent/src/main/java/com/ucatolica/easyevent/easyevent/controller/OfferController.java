package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.offerUser;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.LogService;
import com.ucatolica.easyevent.easyevent.services.OfferUserService;
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

@RestController
public class OfferController {

    public OfferController(OfferUserService offerUserService, EmailService emailService) {
        this.offerUserService = offerUserService;
        this.emailService = emailService;
    }

    private OfferUserService offerUserService;
    private EmailService emailService;

    @GetMapping("/getOffers")
    public List<offerUser> getAll(){
        return offerUserService.getAllOffers();
    }

    @GetMapping("/GETBYNAME/{nombreContacto}")
    public ResponseEntity<?> getOfferUser(@PathVariable String nombreContacto, @RequestHeader("Authorization") String authorization) {
        try {
            Optional<offerUser> ofertaGuardada = offerUserService.getOfferUserByName(nombreContacto);

            if (!JwtUtil.verifyToken(authorization)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return ResponseEntity.ok().body(ofertaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Guarda la oferta de empleo",
            description = "Guarda la oferta de empleo y envía una notificación por correo al cliente",
            tags = {"offer", "saveOfferUser"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Se ha guardado exitosamente la oferta de empleo y se ha enviado una notificación por correo"),
            @ApiResponse(responseCode = "403", description = "Falló el guardado de la oferta de empleo"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Parameters({
            @Parameter(name="nombreContacto", description="Nombre del contacto", example="Juan Pérez"),
            @Parameter(name="telefonoContacto", description="Teléfono del contacto", example="3214567890"),
            @Parameter(name="correoContacto", description="Correo electrónico del contacto", example="juan.perez@example.com"),
            @Parameter(name="cargoOferta", description="Cargo que se ofrece", example="Desarrollador de software"),
            @Parameter(name="salarioOferta", description="Salario que se ofrece", example="1.000.000 pesos colombianos"),
            @Parameter(name="horarioOferta", description="Horario de la oferta", example="Lunes a viernes de 8:00 a.m. a 6:00 p.m."),
            @Parameter(name="lugarOferta", description="Lugar de la oferta", example="Medellín, Colombia")
    })
    @Autowired
    private LogService logService;
    @PostMapping("/saveOfferUser")
    public ResponseEntity<?> saveOfferUser(@RequestBody offerUser offeruser, @RequestBody Evento evento, @RequestBody HttpServletRequest request) {
        try {
            offerUser ofertaGuardada = offerUserService.saveOfferUser(offeruser);

            String token = JwtUtil.generateToken(offeruser.getNombreContacto());

            if (ofertaGuardada != null && evento != null){
                emailService.sendEmail(
                        offeruser.getCorreoContacto(),
                        "Guardado exitoso",
                        "Hola "+offeruser.getNombreContacto()+"; Tu oferta de empleo para el proveedor " +evento.getNombreEvento()+" ha sido guardado con exito",
                        "C:\\Users\\User\\Desktop\\ProyectoCS\\EasyEvent\\easyevent\\src\\main\\java\\com\\ucatolica\\easyevent\\easyevent\\img\\Success.png");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            logService.logActivity("username", "Offer Saved", "No exception", getClientIp(request));

            return ResponseEntity.ok().body("Oferta creada\nToken: " + token);
        } catch (Exception e) {
            logService.logActivity("username", "Offer Save Failed", e.getMessage(), getClientIp(request));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
