package com.studies.algafood.jpa.restaurant;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.Restaurant;
import com.studies.algafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeleteRestaurant {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = restaurantRepository.find(1L);

        restaurantRepository.remove(restaurant1);

        restaurantRepository.list().forEach(System.out::println);
    }
}
