package com.studies.algafood.jpa.paymentMethod;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.PaymentMethod;
import com.studies.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PaymentMethodCrud {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository paymentMethodRepository = applicationContext.getBean(PaymentMethodRepository.class);

        List<PaymentMethod> paymentMethodList = paymentMethodRepository.list();

        paymentMethodList.forEach(System.out::println);

        PaymentMethod paymentMethod = paymentMethodRepository.find(2L);

        System.out.println(paymentMethod);

        PaymentMethod newPaymentMethod = new PaymentMethod("PIX");

        newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);

        System.out.println(newPaymentMethod);

        newPaymentMethod.setDescription("PIX Atualizado");

        newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);
        System.out.println(newPaymentMethod);

        paymentMethodRepository.list().forEach(System.out::println);

        paymentMethodRepository.remove(newPaymentMethod);

        paymentMethodRepository.list().forEach(System.out::println);
    }
}
