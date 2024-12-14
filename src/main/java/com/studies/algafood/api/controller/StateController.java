package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.StateRepository;
import com.studies.algafood.domain.service.StateRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegisterService;

    @GetMapping
    public ResponseEntity<List<State>> list(){
        List<State> result = this.stateRepository.list();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> find(@PathVariable Long stateId){
        State state = this.stateRepository.find(stateId);

        if (state != null){
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<State> save(@RequestBody State state){
        state =  this.stateRegisterService.save(state);

        return ResponseEntity.ok(state);
    }
}
