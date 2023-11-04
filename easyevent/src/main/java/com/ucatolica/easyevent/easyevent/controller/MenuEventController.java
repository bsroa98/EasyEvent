package com.ucatolica.easyevent.easyevent.controller;
import com.ucatolica.easyevent.easyevent.entities.MenuEvent;
import com.ucatolica.easyevent.easyevent.entities.MenuEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuEventController {
    private final MenuEventRepository menuEventRepository;

    @Autowired
    public MenuEventController(MenuEventRepository menuEventRepository) {
        this.menuEventRepository = menuEventRepository;
    }

    @PostMapping
    public MenuEvent createMenuEvent(@RequestBody MenuEvent menuEvent) {
        return menuEventRepository.save(menuEvent);
    }

    @GetMapping("/{menuEventId}")
    public MenuEvent getMenuEvent(@PathVariable Long menuEventId) {
        return (MenuEvent) menuEventRepository.findById(menuEventId)
                .orElseThrow(() -> new IllegalArgumentException("MenuEvent not found"));
    }

    @GetMapping
    public Iterable<MenuEvent> getAllMenuEvents() {
        return menuEventRepository.findAll();
    }


    @PutMapping("/{menuEventId}")
    public MenuEvent updateMenuEvent(@PathVariable Long menuEventId, @RequestBody MenuEvent updatedMenuEvent) {
        MenuEvent menuEvent = (MenuEvent) menuEventRepository.findById(menuEventId)
                .orElseThrow(() -> new IllegalArgumentException("MenuEvent not found"));
        menuEvent.setName(updatedMenuEvent.getName());

        menuEvent.setPrecio(updatedMenuEvent.getPrecio());
        return menuEventRepository.save(menuEvent);
    }

    
}