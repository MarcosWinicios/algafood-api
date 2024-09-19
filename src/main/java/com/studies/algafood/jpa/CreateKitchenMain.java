package com.studies.algafood.jpa;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CreateKitchenMain {
    public static void main(String[] args) {
        /**
         * Prepara um contexto da aplicação para não executar como uma aplicação Web
         */
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegister kitchenRegister =  applicationContext.getBean(KitchenRegister.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brasileira");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("Japonesa");

        kitchen1 = kitchenRegister.addKitchen(kitchen1);
        kitchen2 = kitchenRegister.addKitchen(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());

    }
}