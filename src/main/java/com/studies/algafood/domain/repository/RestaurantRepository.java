package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> list();
    Restaurant find(Long id);
    Restaurant save(Restaurant restaurant);
    void remove(Restaurant restaurant);
}
