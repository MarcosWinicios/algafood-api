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

    public static final String MSG_STATE_NOT_FOUND = "There are no state registered with code %d";
    public static final String MSG_STATE_IN_USE = "The state at code %d cannot be removed because it is in use";

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {
        return this.stateRepository.save(state);
    }

    public void remove(Long id) {
        try {
            if (!this.stateRepository.existsById(id)) {
                throw new EntityNotFoundException(
                        String.format(MSG_STATE_NOT_FOUND, id)
                );
            }
            this.stateRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_STATE_IN_USE, id)
            );
        }
    }

    public State findOrFail(Long stateId) {
        return this.stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_STATE_NOT_FOUND, stateId)
                ));
    }
}
