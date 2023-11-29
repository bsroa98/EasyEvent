package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.ExceptionHandler.*;
import com.ucatolica.easyevent.easyevent.entities.Cliente;
import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.Reserva;
import com.ucatolica.easyevent.easyevent.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="reserva", description = "Esta API gestiona las operaciones sobre la entidad Reserva")
@RestController
public class ReservaController {
    public ReservaController(ReservaService reservaService, EmailService emailService, EventService eventService, ClientService clientService) {
        this.reservaService = reservaService;
        this.emailService = emailService;
        this.eventService = eventService;
        this.clientService = clientService;
    }

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @GetMapping("/reservas")
    public List<Reserva> getALl(){return reservaService.getAllReserva();}

    @GetMapping("/reservas/{id}")
    public ResponseEntity<?> getReserva(@PathVariable int id){
        try {
            Optional<Reserva> reservaOptional= reservaService.getReservaById(id);
            if (reservaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(reservaOptional.get());
            }
            else {
                throw new ResourceNotFoundException("Reserva no encontrada");
            }
        }
        catch (ResourceNotFoundException ex){
            ResponseEntity<ErrorResponse> errorResponse = globalExceptionHandler.handleResourceNotFoundException(ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        }
    }

    @Operation(summary = "Guarda la reserva",
            description = "Guarda la reserva y envía una notificación por correo al cliente",
            tags = {"reserva", "Reserva"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Se ha guardado exitosamente la reserva y se ha enviado una notificación por correo"),
            @ApiResponse(responseCode = "403", description = "Falló el guardado de la reserva"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Parameters({
            @Parameter(name="precioTotal", description="Precio Total de la Reserva", example="500000"),
            @Parameter(name="fechaevento", description="Fecha del Evento", example="2023-12-07"),
            @Parameter(name="abono", description="Cotizacion del Evento", example="250000")
    })

    @PostMapping("/reservas/save")
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva){
        try{
            ResponseEntity<?> reservaGuardada = reservaService.saveReserva(reserva);
            if (reservaGuardada.getBody()=="errorFecha"){
                throw new ForbiddenException("Fecha inexistente");
            }
            if (reservaGuardada.getBody()=="errorVerificacion"){
                throw new ForbiddenException("Usuario no verificado");
            }
            if (reservaGuardada.getBody()=="errorDisp"){
                throw new ForbiddenException("Fecha ocupada");
            }
            if (reservaGuardada.getBody()=="errorAbono"){
                throw new ForbiddenException("Abono insuficiente");
            }
            if (reservaGuardada.getBody()=="Id Nulo"){
                throw new BadRequestException("id no puede ser nulo");
            }
            if (reservaGuardada.getBody()=="Id Erroneo"){
                throw new BadRequestException("IdErroneo");
            }
            Evento eventoid = reserva.getEventoid();
            Optional<Evento> optionalEvento=eventService.getEventoById(eventoid.getId());
            Cliente clienteid = reserva.getClienteid();
            Optional<Cliente> optionalCliente=clientService.getClienteById(clienteid.getId());
            if (optionalEvento.isPresent() && optionalCliente.isPresent()){
                Cliente cliente = optionalCliente.get();
                Evento evento = optionalEvento.get();
                emailService.sendTextEmail(cliente.getCorreo(),"Reserva exitosa","Hola "+cliente.getNombre()+"\n Tu reserva para el evento" +evento.getNombreEvento()+" ha sido realizada con exito"
                        +"\n Fecha: "+reserva.getFechaEvento()
                        +"\n Lugar: "+evento.getUbicacion());}
            else{
                throw new ForbiddenException("Correo no enviado");
            }
            return  ResponseEntity.status(HttpStatus.CREATED).body(reservaGuardada);
        }
        catch (ForbiddenException ex) {
            ResponseEntity<ErrorResponse> errorResponse = globalExceptionHandler.handleForbiddenException(ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
        catch(BadRequestException ex){
            ResponseEntity<ErrorResponse> errorResponse = globalExceptionHandler.handleBadRequestException(ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        catch (Exception ex){
            ResponseEntity<ErrorResponse> errorResponse = globalExceptionHandler.handleGenericException(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }
    @DeleteMapping("reservas/del/{id}")
    public ResponseEntity<String> deleteEvento(@PathVariable Integer id) {
        boolean eliminado = reservaService.deleteReservaById(id);

        if (eliminado) {
            return ResponseEntity.ok("Evento eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el evento con el ID proporcionado");
        }
    }




}
