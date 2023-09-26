package com.ucatolica.easyevent.easyevent;

import com.ucatolica.easyevent.easyevent.entities.ProveedorCrudRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public abstract class EasyeventApplication implements ProveedorCrudRepository{

	public static void main(String[] args) {
		SpringApplication.run(EasyeventApplication.class, args);


	}

}
