package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> list(){
        /**
         * Utilizando JPQL para realizar consultas no banco de dados
         */
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList(); // Busca uma lista de objetos do tipo Kitchen
    }

    @Override
    public Kitchen find(Long id){
        return manager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen){
        return manager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(Kitchen kitchen){
        kitchen = find(kitchen.getId());
        manager.remove(kitchen);
    }


}
