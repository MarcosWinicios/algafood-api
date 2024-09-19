package com.studies.algafood.jpa;

import com.studies.algafood.AlgafoodApiApplication;

import com.studies.algafood.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class GetKitchenMain {
    public static void main(String[] args) {
        /**
         * Prepara um contexto da aplicação para não executar como uma aplicação Web
         */
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegister kitchenRegister =  applicationContext.getBean(KitchenRegister.class);

        List<Kitchen> kitchenList =  kitchenRegister.list();

        for(Kitchen kitchen : kitchenList){
            System.out.println(kitchen.getName());
        }
    }
}
