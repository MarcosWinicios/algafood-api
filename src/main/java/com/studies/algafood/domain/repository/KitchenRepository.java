package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> list();
    Kitchen find(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Kitchen kitchen);

}
