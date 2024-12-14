package com.studies.algafood.domain.service;

import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateRegisterService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state){
        return this.stateRepository.save(state);
    }
}
