package com.example.ActiveMQ.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private Boolean migrated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity product = (ProductEntity) o;
        return Objects.equals(id, product.id)
                && Objects.equals(name, product.name)
                && Objects.equals(price, product.price)
                && Objects.equals(migrated, product.migrated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, migrated);
    }
}
