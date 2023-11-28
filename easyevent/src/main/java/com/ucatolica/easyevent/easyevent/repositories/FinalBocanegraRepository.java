package com.ucatolica.easyevent.easyevent.repositories;

import com.ucatolica.easyevent.easyevent.entities.FinalBocanegra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class FinalBocanegraRepository implements Serializable{

    @Autowired
    private FinalBocanegraCrudRepository FinalBocanegraCrudRepository;

    public FinalBocanegraRepository(com.ucatolica.easyevent.easyevent.repositories.FinalBocanegraCrudRepository FinalBocanegraCrudRepository) {
        this.FinalBocanegraCrudRepository = FinalBocanegraCrudRepository;
    }

    public FinalBocanegra SaveFinalBocanegra(finalBocanegra finalbocanegra){
        return FinalBocanegraCrudRepository.save(finalbocanegra);
    }

}