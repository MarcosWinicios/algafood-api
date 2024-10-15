package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> list();
    PaymentMethod find(Long id);
    PaymentMethod save(PaymentMethod paymentMethod);
    void remove(PaymentMethod paymentMethod);
}
