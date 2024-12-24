package com.studies.algafood.infrastructure.repository.spec;

import com.studies.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {
    //A lambada no return implementa o metodo toPredicate() da interface Specification

    public static Specification<Restaurant> withFreeShipping(){
        return (root, query, builder) ->
                builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSimilarName(String name){
        return (root, query, builder) ->
                builder.like(root.get("name"), "%" + name + "%");
    }
}
