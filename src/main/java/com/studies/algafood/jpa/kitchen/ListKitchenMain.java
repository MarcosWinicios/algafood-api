package com.studies.algafood.jpa.kitchen;

import com.studies.algafood.AlgafoodApiApplication;

import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ListKitchenMain {
    public static void main(String[] args) {
        /**
         * Prepara um contexto da aplicação para não executar como uma aplicação Web
         */
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository =  applicationContext.getBean(KitchenRepository.class);

        List<Kitchen> kitchenList =  kitchenRepository.list();

        for(Kitchen kitchen : kitchenList){
            System.out.println(kitchen.getName());
        }
    }
}
