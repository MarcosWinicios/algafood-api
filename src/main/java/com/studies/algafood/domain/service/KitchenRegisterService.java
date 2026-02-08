package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegisterService {

    public static final String MSG_KITCHEN_NOT_FOUND = "There are no records of any kitchen with code %d";
    public static final String MSG_KITCHEN_IN_USE = "The kitchen at code %d cannot be removed because it is in use";

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return this.kitchenRepository.save(kitchen);
    }

    public void remove(Long kitchenId) {
        try {
            if (!this.kitchenRepository.existsById(kitchenId)) {
                throw new EntityNotFoundException(
                        String.format(MSG_KITCHEN_NOT_FOUND, kitchenId)
                );
            }
            this.kitchenRepository.deleteById(kitchenId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_KITCHEN_IN_USE, kitchenId)
            );
        }
    }

    public Kitchen findOrFail(Long kitchenId) {
        return this.kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_KITCHEN_NOT_FOUND, kitchenId)
                ));
    }
}
