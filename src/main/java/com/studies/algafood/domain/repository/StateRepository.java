package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> list();
    State find(Long id);
    State save(State state);
    void remove(State state);

}
