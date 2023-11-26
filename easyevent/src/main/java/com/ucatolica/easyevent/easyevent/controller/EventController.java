package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.Proveedor;
import com.ucatolica.easyevent.easyevent.services.EmailService;
import com.ucatolica.easyevent.easyevent.services.EventService;
import com.ucatolica.easyevent.easyevent.services.ProveedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@Tag(name="event", description = "Esta API gestiona las operaciones sobre la entidad Evento")
@RestController
public class EventController {


    public EventController(EventService eventService, EmailService emailService,ProveedorService proveedorService) {
        this.eventService = eventService;
        this.emailService = emailService;
        this.proveedorService = proveedorService;
    }

    private EventService eventService;
    private EmailService emailService;

    private ProveedorService proveedorService;


    @GetMapping("/eventos")
    public List<Evento> getAll(){
        return eventService.getAllEvents();
    }

    @GetMapping("/eventos/{id}")
    public Optional<Evento> getEvento(@PathVariable int id){
        return eventService.getEventoById(id);
    }

    @Operation(summary = "Crea un nuevo evento",
            description = "Crea un nuevo evento y envía un notificación por correo al proveedor asociado con dicho evento",
            tags = {"event", "crearEvento"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Se ha creado el evento y se ha enviado el correo exitosamente"),
            @ApiResponse(responseCode = "403", description = "Falló la creación del evento"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Parameters({
            @Parameter(name="nombreEvento", description="Nombre del evento", example="Evento de lanzamiento de nuevo producto"),
            @Parameter(name="descripcion", description="Descripción del evento", example="Evento para presentar el lanzamiento de nuestro nuevo producto innovador"),
            @Parameter(name="fechaEvento", description="Fecha del evento", example="2023-10-12"),
            @Parameter(name="horaEvento", description="Hora del evento", example="10:00 AM"),
            @Parameter(name="lugarEvento", description="Lugar del evento", example="Auditorio principal, Universidad EAFIT"),
            @Parameter(name="idproveedor", description="Identificador del proveedor asociado al evento", example="1")
    })
    @PostMapping("/eventos/save")
    public ResponseEntity<?> crearEvento(@RequestBody Evento evento) {
        try {
            ResponseEntity<Evento> eventoGuardado = eventService.saveEvento(evento);
            Proveedor proveedorTemp = evento.getIdproveedor();
            Optional<Proveedor> optionalProveedor= proveedorService.getProveedorById(proveedorTemp.getId());

            if (optionalProveedor.isPresent()){
            Proveedor proveedor = optionalProveedor.get();
            emailService.sendTextEmail(proveedor.getCorreo(),"Guardado exitoso","Hola "+proveedor.getNombreempresa()+"; Tu evento " +evento.getNombreEvento()+" ha sido guardado con exito");}
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eventos/delete")
    public void deleteEvento(@RequestBody Evento evento){
        eventService.deleteEvento(evento);
    }



}
