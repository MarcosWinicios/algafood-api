package com.studies.algafood.jpa.permission;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.Permission;
import com.studies.algafood.domain.repository.PermissionRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PermissionCrud {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissionRepository permissionRepository = applicationContext.getBean(PermissionRepository.class);

        List<Permission> permissionList = permissionRepository.list();

        permissionList.forEach(System.out::println);

        Permission permission = permissionRepository.find(1L);

        System.out.println(permission);

        Permission newPermission =  permissionRepository.save(new Permission("REMOVE_KITCHEN", "Excluir cozinhas"));
        System.out.println(newPermission);

        newPermission.setDescription("Remover cozinhas");

        newPermission = permissionRepository.save(newPermission);

        permissionRepository.remove(newPermission);

    }
}
