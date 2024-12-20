package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.KitchenRepository;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens/by-name")
    public List<Kitchen> findKitchenByName(@RequestParam("name") String name){
        return kitchenRepository.findTodosByNameContaining(name);
    }

    @GetMapping("/kitchens/unico-by-name")
    public Kitchen findUniqueKitchenByName(@RequestParam("name") String name){
        return kitchenRepository.findByName(name).get();
    }

    @GetMapping("/restaurants/by-shipping-fee")
    public List<Restaurant> restaurantByShippingFee(@RequestParam BigDecimal initialFee, @RequestParam BigDecimal finalFee) {
        return restaurantRepository.findByShippingFeeBetween(initialFee, finalFee);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantByName(@RequestParam String name, @RequestParam Long kitchenId) {
        return restaurantRepository.findByNameContainingAndKitchenId(name, kitchenId);
    }
}
