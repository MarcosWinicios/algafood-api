package com.studies.algafood.api.controller;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
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
import java.util.Optional;

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
    public ResponseEntity<State> find(@PathVariable Long stateId) {
        Optional<State> state = this.stateRepository.findById(stateId);

        if (state.isPresent()) {
            return ResponseEntity.ok(state.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<State> save(@RequestBody State state) {
        state = this.stateRegisterService.save(state);

        return ResponseEntity.ok(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody State state) {
        Optional<State> currentState = this.stateRepository.findById(stateId);

        if (currentState.isPresent()) {
            BeanUtils.copyProperties(state, currentState.get(), "id");
            State savedState = this.stateRegisterService.save(currentState.get());
            return ResponseEntity.ok(savedState);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> delete(@PathVariable Long stateId) {
        try {
            this.stateRegisterService.remove(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}