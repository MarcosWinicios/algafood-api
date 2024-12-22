package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee){
        String jpql = "from Restaurant WHERE name LIKE :name and shippingFee BETWEEN :initialShippingFee AND :finalShippingFee";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("initialShippingFee", initialShippingFee)
                .setParameter("finalShippingFee", finalShippingFee)
                .getResultList();

    }
}
