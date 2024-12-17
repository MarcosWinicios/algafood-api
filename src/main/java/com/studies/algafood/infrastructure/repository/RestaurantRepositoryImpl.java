package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list(){

        return manager.createQuery("from Restaurant", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant find(Long id){
        return manager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant){
        return manager.merge(restaurant);
    }

    @Override
    @Transactional
    public void remove(Restaurant restaurant){
        restaurant = find(restaurant.getId());
        manager.remove(restaurant);
    }


}
