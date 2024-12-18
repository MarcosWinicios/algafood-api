package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.KitchenRepository;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant){
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = this.kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("There is no kitchen record with code %d", kitchenId)
                ));

        restaurant.setKitchen(kitchen);
        return this.restaurantRepository.save(restaurant);
    }

}
