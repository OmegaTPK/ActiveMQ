package com.example.ActiveMQ.repository;

import com.example.ActiveMQ.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByMigrated(Boolean aTrue);
}
