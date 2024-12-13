package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.KitchenRepository;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Beans;

@Service
public class RestaurantRegisterService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant){
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = this.kitchenRepository.find(kitchenId);

        if(kitchen == null){
            throw new EntityNotFoundException(
                    String.format("There is no kitchen record with code %d", kitchenId)
            );
        }

        restaurant.setKitchen(kitchen);
        return this.restaurantRepository.save(restaurant);
    }


    public Restaurant update(Restaurant restaurant) {
        Restaurant currentRestaurant = this.restaurantRepository.find(restaurant.getId());

        if(currentRestaurant == null){
            throw new EntityNotFoundException(
                    String.format("There is no restaurant with code %d", restaurant.getId())
            );
        }

        Kitchen kitchen = this.kitchenRepository.find(restaurant.getKitchen().getId());

        if(kitchen == null){
            throw new EntityNotFoundException(
                    String.format("There is no kitchen record with code %d", restaurant.getKitchen().getId())
            );
        }

        restaurant.setKitchen(kitchen);
        BeanUtils.copyProperties(restaurant, currentRestaurant);

        return this.restaurantRepository.save(currentRestaurant);
    }
}
