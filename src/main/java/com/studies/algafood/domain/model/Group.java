package com.studies.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_group")
public class Group {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

}
