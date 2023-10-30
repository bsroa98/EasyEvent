package com.ucatolica.easyevent.easyevent.entities;

import java.util.List;

public interface MenuEventRepository {
    MenuEvent save(MenuEvent menuEvent);

    <T> ScopedValue<T> findById(Long menuEventId);

    List<MenuEvent> findAll();
}
