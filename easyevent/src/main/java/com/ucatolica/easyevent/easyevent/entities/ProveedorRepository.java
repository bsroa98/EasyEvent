package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProveedorRepository {
    @Autowired
    private ProveedorCrudRepository proveedorCrudRepository;

    public List<Proveedor> getAll(){return (List<Proveedor>) proveedorCrudRepository.findAll();}
    public Optional<Proveedor> getProveedor(int id){return proveedorCrudRepository.findById(id);}

    public Proveedor save(Proveedor proveedor){return proveedorCrudRepository.save(proveedor);}

    public void delete(Proveedor proveedor){proveedorCrudRepository.delete(proveedor);}

}
