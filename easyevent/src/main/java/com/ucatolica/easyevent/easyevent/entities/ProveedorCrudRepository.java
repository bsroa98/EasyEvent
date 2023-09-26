package com.ucatolica.easyevent.easyevent.entities;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
public interface ProveedorCrudRepository extends CrudRepository<Proveedor,Integer> {
    @Query(value = "SELECT * FROM proveedores", nativeQuery = true)
    default List<Proveedor> findAllNative() {
        return null;
    }
    @Query("SELECT nit FROM proveedores p WHERE p.nombreEmpresa = true")
    default List<Proveedor> findBynombreEmpresa(){
        return null;
    }

}
