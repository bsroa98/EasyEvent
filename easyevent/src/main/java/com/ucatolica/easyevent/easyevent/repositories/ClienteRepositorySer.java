package com.ucatolica.easyevent.easyevent.repositories;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucatolica.easyevent.easyevent.entities.Cliente;

@Repository
public class ClienteRepositorySer implements Serializable {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;
    public List<Cliente> getAll(){return (List<Cliente>) clienteCrudRepository.findAll();}

    public Optional<Cliente> getCliente(int id){return clienteCrudRepository.findById(id);}

    public Cliente save(Cliente cliente){return clienteCrudRepository.save(cliente);}

    public void delete(Cliente cliente){clienteCrudRepository.delete(cliente);}

}

