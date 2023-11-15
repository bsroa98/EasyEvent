package com.ucatolica.easyevent.easyevent.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucatolica.easyevent.easyevent.model.Cliente;
import com.ucatolica.easyevent.easyevent.repositories.ClienteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByUsername(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nombre));

        return UserDetailsImpl.build(cliente);
    }
}
