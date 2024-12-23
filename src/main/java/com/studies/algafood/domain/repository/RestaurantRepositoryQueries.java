package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> findWithParamsJpql(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);

    List<Restaurant> findWithParamsCriteriaAPI(String name, BigDecimal initialShippingFee, BigDecimal finalShippingFee);
}
