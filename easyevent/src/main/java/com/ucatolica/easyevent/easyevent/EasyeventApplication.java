package com.ucatolica.easyevent.easyevent;

import com.ucatolica.easyevent.easyevent.persitencia.ProveedorCrudRepository;
import com.ucatolica.easyevent.easyevent.persitencia.ProveedorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ucatolica.easyevent.easyevent.persitencia.ProveedorCrudRepository;
@SpringBootApplication
public abstract class EasyeventApplication implements ProveedorCrudRepository{

	public static void main(String[] args) {
		SpringApplication.run(EasyeventApplication.class, args);


	}

}
