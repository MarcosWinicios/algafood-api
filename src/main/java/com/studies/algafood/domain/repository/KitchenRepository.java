package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

    List<Kitchen> findTodosByNameContaining(String name);

    Optional<Kitchen> findByName(String name);

    boolean existsByName(String name);

}
