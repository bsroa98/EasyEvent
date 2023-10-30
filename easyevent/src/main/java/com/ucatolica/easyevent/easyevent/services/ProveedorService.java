package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.Proveedor;
import com.ucatolica.easyevent.easyevent.entities.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProveedorService {

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    private ProveedorRepository proveedorRepository;

    public Optional<Proveedor> getProveedorById(int id) {return proveedorRepository.getProveedor(id);}
}
