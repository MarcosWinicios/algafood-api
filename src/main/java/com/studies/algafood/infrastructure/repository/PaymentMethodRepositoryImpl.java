package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.PaymentMethod;
import com.studies.algafood.domain.repository.PaymentMethodRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentMethod> list() {
        return entityManager.createQuery("from PaymentMethod", PaymentMethod.class).getResultList();
    }

    @Override
    public PaymentMethod find(Long id) {
        return entityManager.find(PaymentMethod.class, id);
    }

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
       return  entityManager.merge(paymentMethod);
    }

    @Override
    @Transactional
    public void remove(PaymentMethod paymentMethod) {
        paymentMethod = find(paymentMethod.getId());
        entityManager.remove(paymentMethod);
    }
}
