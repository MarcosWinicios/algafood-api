package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> queryByShippingFeeBetween(BigDecimal initialFee, BigDecimal finalFee);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);
}
