package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee){
//        String jpql = "from Restaurant WHERE name LIKE :name and shippingFee BETWEEN :initialShippingFee AND :finalShippingFee";

        StringBuilder jpql = new StringBuilder();

        Map<String, Object> parameters = new HashMap<>();

        jpql.append("from Restaurant WHERE 0 = 0");

        if(StringUtils.hasLength(name)){
            jpql.append("AND name LIKE :name ");
            parameters.put("name", "%" + name + "%");
        }

        if(initialShippingFee != null){
            jpql.append("AND shippingFee >= :initialShippingFee ");
            parameters.put("initialShippingFee", initialShippingFee);
        }

        if(finalShippingFee != null){
            jpql.append("AND shippingFee <= :finalShippingFee ");
            parameters.put("finalShippingFee", finalShippingFee);
        }

        TypedQuery<Restaurant> query =  manager.createQuery(jpql.toString(), Restaurant.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));

        return query.getResultList();
    }
}
