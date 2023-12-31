package com.ucatolica.easyevent.easyevent.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucatolica.easyevent.easyevent.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsername(String nombre);

    Boolean existsByUsername(String username);

    Boolean existsByCorreo(String correo);
}

