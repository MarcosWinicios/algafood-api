package com.studies.algafood.infrastructure.repository;

import com.studies.algafood.domain.model.Permission;
import com.studies.algafood.domain.repository.PermissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permission> list() {
        return entityManager.createQuery("from Permission", Permission.class).getResultList();
    }

    @Override
    public Permission find(Long id) {
        return entityManager.find(Permission.class, id);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        return entityManager.merge(permission);
    }

    @Override
    @Transactional
    public void remove(Permission permission) {
        permission = find(permission.getId());
        entityManager.remove(permission);
    }
}
