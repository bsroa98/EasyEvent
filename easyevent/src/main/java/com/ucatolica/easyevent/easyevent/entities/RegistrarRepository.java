package com.ucatolica.easyevent.easyevent.entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class RegistrarRepository implements Serializable {
    @Autowired
    private RegistrarCrudRepository registrarCrudRepository;


    public List<Registrar> getAll(){return (List<Registrar>) registrarCrudRepository.findAll();}

    public Registrar saveRegistrar(Registrar registrar){return registrarCrudRepository.save(registrar);}

 
}

