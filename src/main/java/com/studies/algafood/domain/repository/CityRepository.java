package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();
    City find(Long id);
    City save(City city);
    void remove(City city);
}
