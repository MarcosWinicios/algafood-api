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
import java.util.Optional;

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

    @GetMapping("/kitchens/first")
    public Optional<Kitchen> kitchenFindFirst(){
        return kitchenRepository.findFirst();
    }


    @GetMapping("/kitchens/exists")
    public boolean kitchenCheckExists(@RequestParam("name") String name){
        return kitchenRepository.existsByName(name);
    }

    @GetMapping("/restaurants/by-shipping-fee")
    public List<Restaurant> restaurantByShippingFee(@RequestParam BigDecimal initialFee, @RequestParam BigDecimal finalFee) {
        return restaurantRepository.queryByShippingFeeBetween(initialFee, finalFee);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantByName(@RequestParam String name, @RequestParam Long kitchenId) {
        return restaurantRepository.findByName(name, kitchenId);
    }

    @GetMapping("/restaurants/first-by-name")
    public Restaurant restaurantFirstByName(@RequestParam String name){
        return restaurantRepository.findFirstRestaurantByNameContaining(name).get();
    }

    @GetMapping("/restaurants/top-2-by-name")
    public List<Restaurant> restaurantTop2ByName(@RequestParam String name) {
        return restaurantRepository.findTop2ByNameContaining(name);
    }

    @GetMapping("/restaurants/by-name-and-shipping-fee")
    public List<Restaurant> restaurantByNameAndShippingFee(
            @RequestParam(required = false) String name, @RequestParam(required = false) BigDecimal initialFee, @RequestParam(required = false) BigDecimal finalFee) {
        return restaurantRepository.findWithParamsCriteriaAPI(name, initialFee, finalFee);
//        return restaurantRepository.findWithParamsJpql(name, initialFee, finalFee);
    }

    @GetMapping("/restaurants/count-by-kitchen")
    public int restaurantsCountByKitchen(@RequestParam Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @GetMapping("/restaurants/count-by-kitchen-name")
    public List<Restaurant> restaurantsCountByKitchenName(@RequestParam String kitchenName) {
        return restaurantRepository.findByKitchenName(kitchenName);
    }

    @GetMapping("/restaurants/with-free-shipping")
    public List<Restaurant> restaurantsWithFreeShipping(String name){
//        return restaurantRepository.findWithFreeShippingCriteria(name);
        return restaurantRepository.findWithFreeShipping(name);
    }

    @GetMapping("/restaurants/first")
    public Optional<Restaurant> restaurantFirst(){
        return restaurantRepository.findFirst();
    }

}
