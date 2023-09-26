package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProveedorRepository {

    @Autowired
    private ProveedorCrudRepository ProveedorCrudRepository;

    public List<Proveedor> getAll(){return (List<Proveedor>) ProveedorCrudRepository.findAll();}
    public Optional<Proveedor> getProveedor(int id){return ProveedorCrudRepository.findById(id);}

    public Proveedor save(Proveedor proveedor){return ProveedorCrudRepository.save(proveedor);}

    public void delete(Proveedor proveedor){ProveedorCrudRepository.delete(proveedor);}

}
