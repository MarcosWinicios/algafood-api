package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepository;
import com.studies.algafood.domain.repository.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.studies.algafood.infrastructure.repository.spec.RestaurantSpecs.withFreeShipping;
import static com.studies.algafood.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Lazy
    @Autowired
    private RestaurantRepository restaurantRepository;

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
        Root<Restaurant> root = criteria.from(Restaurant.class);
        
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasLength(name)){
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if(initialShippingFee != null){
            predicates.add( builder
                    .greaterThanOrEqualTo(root.get("shippingFee"), initialShippingFee));
        }

        if(finalShippingFee != null){
            predicates.add(builder
                    .lessThanOrEqualTo(root.get("shippingFee"), finalShippingFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query=  manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeShippingCriteria(String name) {

        CriteriaBuilder builder =  manager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(root.get("shippingFee"), BigDecimal.ZERO));

        if(StringUtils.hasLength(name)){
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeShipping(String name) {
        return restaurantRepository.findAll(withFreeShipping().and(withSimilarName(name)));
    }

    @Override
    public List<Restaurant> findByKitchenName(String kitchenName){

        if(!StringUtils.hasLength(kitchenName)){
            throw new IllegalArgumentException("kitchenName param can't null");
        }

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        criteria.where(builder.like(root.get("kitchen").get("name"), "%" + kitchenName + "%"));
        TypedQuery<Restaurant> query = manager.createQuery(criteria);

        return query.getResultList();
    }

}
