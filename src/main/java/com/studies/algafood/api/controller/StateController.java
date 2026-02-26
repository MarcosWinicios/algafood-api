package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.StateRepository;
import com.studies.algafood.domain.service.StateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<State>> list() {
        List<State> result = this.stateRepository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{stateId}")
    public State find(@PathVariable Long stateId) {
        return this.stateRegisterService.findOrFail(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<State> save(@RequestBody State state) {
        state = this.stateRegisterService.save(state);

        return ResponseEntity.ok(state);
    }

    @PutMapping("/{stateId}")
    public State update(@PathVariable Long stateId, @RequestBody State state) {
        State currentState = this.stateRegisterService.findOrFail(stateId);
        BeanUtils.copyProperties(state, currentState, "id");
        return this.stateRegisterService.save(currentState);
    }


    @DeleteMapping("/{stateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long stateId) {
        this.stateRegisterService.remove(stateId);
    }
}