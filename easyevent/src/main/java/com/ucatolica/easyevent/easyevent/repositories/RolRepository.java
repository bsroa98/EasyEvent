package com.ucatolica.easyevent.easyevent.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucatolica.easyevent.easyevent.entities.Rol;
import com.ucatolica.easyevent.easyevent.entities.Erol;


@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
