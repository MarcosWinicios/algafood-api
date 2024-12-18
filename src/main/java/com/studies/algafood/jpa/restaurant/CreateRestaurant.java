package com.studies.algafood.jpa.restaurant;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.KitchenRepository;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class CreateRestaurant {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen = kitchenRepository.findById(1L)
                .orElseThrow();

        Restaurant restaurant1 = new Restaurant("Restaurant 1", new BigDecimal("10.5"), kitchen);
        restaurant1 = restaurantRepository.save(restaurant1);

        List<Restaurant> restaurantList = restaurantRepository.list();

        restaurantList.forEach(System.out::println);

        System.out.println(restaurantList.getLast().equals(restaurant1));

        restaurant1.setName(restaurant1.getName() +  " Atualizado");
        restaurantRepository.save(restaurant1);

//        kitchenRepository.remove(kitchen.getId());

//        restaurantRepository.remove(restaurant1);

//        Restaurant restaurant2 = new Restaurant();
//        restaurantRepository.save(restaurant2);

    }
}
