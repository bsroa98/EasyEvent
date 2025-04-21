package com.ucatolica.easyevent.easyevent.repositories;

import com.ucatolica.easyevent.easyevent.entities.Cliente;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {
        Optional<Cliente> findByUsername(String username);
}
