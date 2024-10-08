package com.studies.algafood.jpa.city;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.City;
import com.studies.algafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CityMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository allCities = applicationContext.getBean(CityRepository.class);

        List<City> cityList = allCities.list();

        cityList.forEach(System.out::println);
    }
}
