package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);
}
