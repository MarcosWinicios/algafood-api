package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.City;
import com.studies.algafood.domain.repository.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<City> list() {
        return entityManager.createQuery("from City", City.class).getResultList();
    }

    @Override
    public City find(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void remove(City city) {
        entityManager.remove(city);
    }
}
