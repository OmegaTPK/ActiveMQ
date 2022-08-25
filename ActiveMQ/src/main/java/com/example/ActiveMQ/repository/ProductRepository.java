package com.example.ActiveMQ.repository;

import com.example.ActiveMQ.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByMigrated(Boolean aTrue);


}
