package com.studies.algafood.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_restaurant")
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal shippingFee;

    @Column(nullable = false)
    private Boolean isOpen = true;

    @Column(nullable = false)
    private Boolean isActive = true;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    public Restaurant(String name, BigDecimal shippingFee, Kitchen kitchen) {
        this.name = name;
        this.shippingFee = shippingFee;
        this.kitchen = kitchen;
        this.isActive = true;
        this.isOpen = true;
    }
}
