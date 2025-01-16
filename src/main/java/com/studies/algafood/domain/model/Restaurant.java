package com.studies.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_restaurant_payment_method",
        joinColumns = @JoinColumn(name = "restaurant_id"),
        inverseJoinColumns = @JoinColumn(name = "payment_method_id")
    )
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    public Restaurant(String name, BigDecimal shippingFee, Kitchen kitchen) {
        this.name = name;
        this.shippingFee = shippingFee;
        this.kitchen = kitchen;
        this.isActive = true;
        this.isOpen = true;
    }
}
