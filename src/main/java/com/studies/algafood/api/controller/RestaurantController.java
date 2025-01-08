package com.studies.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepository;
import com.studies.algafood.domain.service.RestaurantRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantRegisterService restaurantRegisterService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> list() {
        List<Restaurant> restaurants = this.restaurantRepository.findAll();
//        restaurants.stream()
//                .map(x -> x.getPaymentMethods().stream()
//                        .toList())
//                .toList()
//                .forEach(System.out::println);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> find(@PathVariable Long restaurantId) {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId).orElse(null);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = this.restaurantRegisterService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> delete(@PathVariable Long restaurantId){
        try{
            this.restaurantRegisterService.remove(restaurantId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (EntityInUseException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {

        try {
            Restaurant currentRestaurant = this.restaurantRepository.findById(restaurantId).orElse(null);

            if (currentRestaurant != null) {
                BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "address", "createdAt");
                currentRestaurant = this.restaurantRegisterService.save(currentRestaurant);
                return ResponseEntity.ok(currentRestaurant);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Restaurant currentRestaurant = this.restaurantRepository.findById(restaurantId).orElse(null);

        if (currentRestaurant == null) {
            return ResponseEntity.notFound().build();
        }
        merge(fields, currentRestaurant);

        return update(restaurantId, currentRestaurant);
    }

    private void merge(Map<String, Object> dataSource, Restaurant restaurantTarget) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant restaurantSource = objectMapper.convertValue(dataSource, Restaurant.class); //Usando o objectMapper para converter valores de tipos desconhecidos

        dataSource.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, fieldName); //findField() Retorna uma instância de uma propriedade
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, restaurantSource); //getFiedl() Obtem o valor de uma propriedade

//            System.out.println(fieldName + " = " + fieldValue + " = " + newValue);

            ReflectionUtils.setField(field, restaurantTarget, newValue);
        });
    }

}
