package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class StateRegisterService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return this.stateRepository.save(state);
    }

    public void remove(Long id) {
        try {
            if (!this.stateRepository.existsById(id)) {
                throw new EntityNotFoundException(
                        String.format("There is no state record with code %d", id)
                );
            }
            this.stateRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("The state at code %d cannot be removed because it is in use", id)
            );
        }
    }
}
