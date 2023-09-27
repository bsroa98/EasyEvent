package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegistrarProveedorRepository {
    @Autowired
    private RegistrarProveedorRepository registrarProveedorRepository;

    public List<RegistrarProveedor> getAll(){return (List<RegistrarProveedor>) registrarProveedorRepository.findAll();}

    public Registrar saveRegistrarProveedor(Registrar registrar){return registrarProveedorRepository.save(registrar);}


}

