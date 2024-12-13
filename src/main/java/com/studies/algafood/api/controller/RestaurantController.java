package com.studies.algafood.api.controller;

import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepository;
import com.studies.algafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> list() {
        List<Restaurant> restaurants = this.restaurantRepository.list();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> find(@PathVariable Long restaurantId) {
        Restaurant restaurant = this.restaurantRepository.find(restaurantId);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant){

        try {
            restaurant = this.restaurantRegisterService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){

        try {
            Restaurant currentRestaurant = this.restaurantRepository.find(restaurantId);

            if(currentRestaurant != null){
               BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
               currentRestaurant = this.restaurantRegisterService.save(currentRestaurant);
               return  ResponseEntity.ok(currentRestaurant);
            }

            return ResponseEntity.notFound().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
