package com.studies.algafood.infrastructure.repository.spec;

import com.studies.algafood.domain.model.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.math.BigDecimal;

@AllArgsConstructor
public class RestaurantWithSimilarNameSpec implements Specification<Restaurant> {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("name"), "%" + this.name + "%");
    }
}
