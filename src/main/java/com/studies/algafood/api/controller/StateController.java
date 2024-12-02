package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<State> list(){
        return this.stateRepository.list();
    }
}
