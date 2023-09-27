package com.ucatolica.easyevent.easyevent.entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegistrarRepository {
    @Autowired
    private RegistrarRepository registrarRepository;

    public List<Registrar> getAll(){return (List<Registrar>) registrarRepository.findAll();}

    public Registrar saveRegistrar(Registrar registrar){return registrarRepository.save(registrar);}


}

