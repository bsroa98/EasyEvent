package com.ucatolica.easyevent.easyevent.controller;

import com.ucatolica.easyevent.easyevent.entities.Cliente;
import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.entities.Proveedor;
import com.ucatolica.easyevent.easyevent.entities.Reserva;
import com.ucatolica.easyevent.easyevent.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    private ProveedorService proveedorService;

    @GetMapping("/reservas")
    public List<Reserva> getALl(){return reservaService.getAllReserva();}

    @GetMapping("/reservas/{id}")
    public Optional<Reserva> getReserva(@PathVariable int id){
        return reservaService.getReservaById(id);
    }

    @PostMapping("/reservas/save")
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva){
        try{
            ResponseEntity<Reserva> reservaGuardada = reservaService.saveReserva(reserva);
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
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return  ResponseEntity.status(HttpStatus.CREATED).body(reservaGuardada);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

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
