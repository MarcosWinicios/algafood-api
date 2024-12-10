package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegisterService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return this.kitchenRepository.save(kitchen);
    }

    public void remove(Long kitchenId) {
        try {
            this.kitchenRepository.remove(kitchenId);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException(
                    String.format("There is no kitchen record with code %d", kitchenId)
            );
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("The kitchen at code %d cannot be removed because it is in use", kitchenId)
            );
        }
    }
}
