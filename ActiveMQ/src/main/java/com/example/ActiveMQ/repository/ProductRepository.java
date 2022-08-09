package com.example.ActiveMQ.repository;

import com.example.ActiveMQ.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.LockModeType;
import java.time.Instant;
import java.util.List;

@Component
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByMigrated(Boolean aTrue);

    @Query("select p from ProductEntity p where p.creationDate < :#{#date}")
    Page<ProductEntity> findProductForMigration(Pageable  pageable, @Param("date") Instant creationDate);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductEntity p where p.id IN :#{#list}")
    List<ProductEntity> findByMigrationList(@Param("list") List<Long> listId);
}
