package com.ucatolica.easyevent.easyevent.offeruser;

import com.ucatolica.easyevent.easyevent.offeruser.offerUser;
import com.ucatolica.easyevent.easyevent.offeruser.OfferUserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ofertaUsuario")
public class OfferController {
    public OfferController(OfferUserRepository offerUserRepository) {
        this.offerUserRepository = offerUserRepository;
    }

    private final OfferUserRepository offerUserRepository;


    @GetMapping("/GET")
    public List<offerUser> getAll(){
        return offerUserRepository.getAll();
    }

    @GetMapping("/GETBYNAME/{nombreContacto}")
    public Optional<offerUser> getOfferUser(@PathVariable String nombreContacto){
        return offerUserRepository.getOfferUser(nombreContacto);
    }

    /*@PostMapping("/SAVE")
    public Optional<offerUser> saveofferUser(String nombreContacto){
        return offerUserRepository.saveofferUser(nombreContacto);
    }*/
}
