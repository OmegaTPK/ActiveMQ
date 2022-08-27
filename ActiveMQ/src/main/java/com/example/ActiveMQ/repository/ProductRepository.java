package com.example.ActiveMQ.repository;

import com.example.ActiveMQ.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByMigrated(Boolean aTrue);

    @Modifying
    @Query("update ProductEntity p set p.migrated = true where p.id in :idList")
    void setMigratedTrueByIdList(@Param(value = "idList") List<Long> productIds);
}
