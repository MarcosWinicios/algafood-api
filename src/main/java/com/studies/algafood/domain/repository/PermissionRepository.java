package com.studies.algafood.domain.repository;

import com.studies.algafood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();
    Permission find(Long id);
    Permission save(Permission permission);
    void remove(Permission permission);
}
