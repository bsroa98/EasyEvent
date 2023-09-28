package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegistrarProveedorRepository {
    @Autowired
    private RegistrarProveedorRepository registrarCrudRepositoryP;

    public List<RegistrarProveedor> getAll(){return (List<RegistrarProveedor>) registrarCrudRepositoryP.findAll();}

    private Object findAll() {
        return null;
    }

    public Registrar saveRegistrarProveedor(Registrar registrar){return registrarCrudRepositoryP.save(registrar);}

    private Registrar save(Registrar registrar) {
        return null;
    }


}

