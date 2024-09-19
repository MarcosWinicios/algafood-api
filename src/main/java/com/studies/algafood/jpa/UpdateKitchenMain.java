package com.studies.algafood.jpa;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateKitchenMain {
    public static void main(String[] args) {
        /**
         * Prepara um contexto da aplicação para não executar como uma aplicação Web
         */
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegister kitchenRegister =  applicationContext.getBean(KitchenRegister.class);

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setName("Brasileira");


        kitchen = kitchenRegister.save(kitchen);


        System.out.printf("%d - %s\n", kitchen.getId(), kitchen.getName());


    }
}