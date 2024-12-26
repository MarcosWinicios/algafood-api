package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant,Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

    List<Restaurant> queryByShippingFeeBetween(BigDecimal initialFee, BigDecimal finalFee);

    List<Restaurant> findByName(String name, @Param("kitchenId") Long kitchen);

//    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    List<Restaurant> findTop2ByNameContaining(String name);

    int countByKitchenId(Long kitchenId);

}
