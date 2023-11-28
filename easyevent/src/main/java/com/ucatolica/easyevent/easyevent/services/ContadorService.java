package com.ucatolica.easyevent.easyevent.services;

import org.springframework.stereotype.Component;

@Component
public class ContadorService {

    private int count;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void incrementarContador() {
        count++;
    }

    public synchronized void resetearContador() {
        count = 0;
    }
}