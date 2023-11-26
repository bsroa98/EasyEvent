package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.Cliente;
import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.repositories.ClienteRepository;
import com.ucatolica.easyevent.easyevent.repositories.ClienteRepositorySer;
import com.ucatolica.easyevent.easyevent.repositories.EventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ZERO;

@Service
public class ClientService {
    private ClienteRepositorySer clienteRepositorySer;

    public ClientService(ClienteRepositorySer clienteRepositorySer) {
        this.clienteRepositorySer = clienteRepositorySer;
    }

    public List<Cliente> getAllClients() {
        return clienteRepositorySer.getAll();
    }

    public Optional<Cliente> getClienteById(int id) {
        return clienteRepositorySer.getCliente(id);
    }

    public ResponseEntity<Cliente> saveCliente(Cliente cliente) {
        Cliente savedCliente = clienteRepositorySer.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    public void deleteCliente(Cliente cliente){
        clienteRepositorySer.delete(cliente);
    }
}
