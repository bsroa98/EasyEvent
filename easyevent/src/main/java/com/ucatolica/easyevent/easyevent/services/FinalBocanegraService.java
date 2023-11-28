package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.Evento;
import com.ucatolica.easyevent.easyevent.repositories.FinalBocanegraRepository;
import com.ucatolica.easyevent.easyevent.entities.FinalBocanegra;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinalBocanegraService {
    private FinalBocanegraRepository finalBocanegraRepository;

    public FinalBocanegraService(FinalBocanegraRepository finalBocanegraRepository) {
        this.finalBocanegraRepository = finalBocanegraRepository;
    }

    public finalBocanegra SaveFinalBocanegra(finalBocanegra finalbocanegra) {
        return finalBocanegraRepository.SaveFinalBocanegra(finalbocanegra);
    }

}
