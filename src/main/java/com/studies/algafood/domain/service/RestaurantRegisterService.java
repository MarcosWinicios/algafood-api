package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.KitchenRepository;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class RestaurantRegisterService {

    public static final String MSG_KITCHEN_NOT_FOUND = "There are no records of any kitchen with code %d";
    public static final String MSG_RESTAURANT_NOT_FOUND = "There are no records of any restaurant with code %d";
    public static final String MSG_RESTAURANT_IN_USE = "The restaurant at code %d cannot be removed because it is in use";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = this.kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_KITCHEN_NOT_FOUND, kitchenId)
                ));

        restaurant.setKitchen(kitchen);
        return this.restaurantRepository.save(restaurant);
    }

    public void remove(Long restaurantId) {
        try {
            if (!this.restaurantRepository.existsById(restaurantId)) {
                throw new EntityNotFoundException(
                        String.format(MSG_RESTAURANT_NOT_FOUND, restaurantId)
                );
            }
            this.restaurantRepository.deleteById(restaurantId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_RESTAURANT_IN_USE, restaurantId)
            );
        }
    }

    public Restaurant findOrFail(Long restaurantId) {
        return this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_RESTAURANT_NOT_FOUND, restaurantId)
                ));
    }
}
