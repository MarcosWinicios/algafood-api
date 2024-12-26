package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Kitchen;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long>{

    List<Kitchen> findTodosByNameContaining(String name);

    Optional<Kitchen> findByName(String name);

    boolean existsByName(String name);

}
