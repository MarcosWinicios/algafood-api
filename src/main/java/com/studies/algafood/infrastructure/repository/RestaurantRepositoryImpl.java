package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> findWithParamsJpql(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee){

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

        parameters.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithParamsCriteriaAPI(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee){

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        criteria.from(Restaurant.class);

        TypedQuery<Restaurant> query=  manager.createQuery(criteria);
        return query.getResultList();
    }
}
