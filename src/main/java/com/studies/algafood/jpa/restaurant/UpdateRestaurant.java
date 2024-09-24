package com.studies.algafood.jpa.restaurant;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class UpdateRestaurant {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = restaurantRepository.find(1L);
        restaurant1.setName(restaurant1.getName() + " atualizado");
        restaurantRepository.save(restaurant1);

        restaurantRepository.list().forEach(System.out::println);
    }
}
