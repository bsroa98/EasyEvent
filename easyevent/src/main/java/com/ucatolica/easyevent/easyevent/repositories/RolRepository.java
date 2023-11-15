package com.ucatolica.easyevent.easyevent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucatolica.easyevent.easyevent.model.Rol;
import com.ucatolica.easyevent.easyevent.model.Erol;


@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
