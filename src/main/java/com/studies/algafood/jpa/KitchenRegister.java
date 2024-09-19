package com.studies.algafood.jpa;

import com.studies.algafood.domain.model.Kitchen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class KitchenRegister {

    @PersistenceContext
    private EntityManager manager;
    /**
     * A interface EntityManager do JPA Gerencia o contexto de persistência. Responsável pela conversão dos comandos em SQL
     * A anotação @PersistenceContext faz a injeção da classe e fornece mais recursos úteis
     */


    public List<Kitchen> list(){
        /**
         * Utilizando JPQL para realizar consultas no banco de dados
         */
        return manager.createQuery("from Kitchen", Kitchen.class).getResultList(); // Busca uma lista de objetos do tipo Kitchen
    }

    @Transactional
    public Kitchen addKitchen(Kitchen kitchen){
        return manager.merge(kitchen); // cadastra o objeto e retorna a instancia persistida
    }
}
