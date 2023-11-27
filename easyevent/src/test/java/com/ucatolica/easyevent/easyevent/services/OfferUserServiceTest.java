package com.ucatolica.easyevent.easyevent.services;

import com.ucatolica.easyevent.easyevent.entities.offerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OfferUserServiceTest {

    @Autowired
    private OfferUserService offerUserService;

    @Test
    public void getAllOffers() {
        List<offerUser> offers = offerUserService.getAllOffers();
        assertTrue(offers.size() > 0);
    }

}