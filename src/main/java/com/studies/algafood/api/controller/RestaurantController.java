package com.studies.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.ResponseStatus;
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
        return ResponseEntity.ok(this.restaurantRepository.findAll());
    }

    @GetMapping("/{restaurantId}")
    public Restaurant find(@PathVariable Long restaurantId) {
        return this.restaurantRegisterService.findOrFail(restaurantId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return this.restaurantRegisterService.save(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId) {
        this.restaurantRegisterService.remove(restaurantId);
    }

    @PutMapping("/{restaurantId}")
    public Restaurant update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        Restaurant currentRestaurant = this.restaurantRegisterService.findOrFail(restaurantId);
        BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentMethods", "address", "createdAt");
        return this.restaurantRegisterService.save(currentRestaurant);
    }

    @PatchMapping("/{restaurantId}")
    public Restaurant partialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
        Restaurant currentRestaurant = this.restaurantRegisterService.findOrFail(restaurantId);
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
