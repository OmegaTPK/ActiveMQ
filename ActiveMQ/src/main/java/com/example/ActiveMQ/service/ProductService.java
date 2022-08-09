package com.example.ActiveMQ.service;

import com.example.ActiveMQ.activeMq.ActiveMQProducer;
import com.example.ActiveMQ.entity.ProductEntity;
import com.example.ActiveMQ.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.activemq.plugin.ForcePersistencyModeBroker.log;

@Service
@AllArgsConstructor
@Async
public class ProductService {

    private final ProductRepository productRepository;
    private final ActiveMQProducer activeMQProducer;
    private final int PAGE_SIZE = 1000;
    private final String SUCCESS_MESSAGE = "Migrated product with id: ";
    private final String FAIL_MESSAGE = "Not migrated product with id: ";
    private final String FAILD_MIGRATION_TIMES_MESSAGE = ". For now product faild migration times: ";
    private final String CANT_DELETE_MESSAGE = "Cant delete migrated products: ";
    private final String CANT_SAVE_MESSAGE = "Cant save failed migrations: ";

    public void sendingProductsInQueue(){
        Sort sort = Sort.by("id");
        Instant currentInstant = Instant.now();
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, sort);
        Page<ProductEntity> page = productRepository.findProductForMigration(pageable, currentInstant);
        Set<Set<Long>> queue = new HashSet<>();
        while (true) {
            Set<Long> productDtos = page.getContent()
                    .stream()
                    .map(product -> (product.getId()))
                    .collect(Collectors.toSet());
            if (!productDtos.isEmpty()){
                queue.add(productDtos);
            }
            if (!page.hasNext()) {
                break;
            }
            page = productRepository.findProductForMigration(page.nextPageable(), currentInstant);
        }
        if (!queue.isEmpty()){
            queue.stream().forEach(activeMQProducer::sendNonMigratedItems);
        }
    }

    public void generate(Long count){
        List<ProductEntity> entities = new ArrayList<>();
        for (int i = 0; i<count; i++){
            ProductEntity entity = ProductEntity.builder().name("Test").migrated(false).creationDate(Instant.now()).price((double) 0L).build();
            entities.add(entity);
        }
        productRepository.saveAll(entities);
    }

    @Transactional
    public void migrateIncomingProducts(List<Long> listId){
        List<ProductEntity> entities = productRepository.findByMigrationList(listId);
        List<ProductEntity> failedMigration = new ArrayList<>();
        List<ProductEntity> successfulMigration = new ArrayList<>();

        for (ProductEntity entity:entities){
            try{
                entity.setMigrated(true); //doing some migration
                successfulMigration.add(entity);
                log.info(SUCCESS_MESSAGE + entity.getId());
            } catch (Exception e){
                entity.setMigrationFailed(entity.getMigrationFailed()+1);
                failedMigration.add(entity);
                log.info(FAIL_MESSAGE + entity.getId() + FAILD_MIGRATION_TIMES_MESSAGE + entity.getMigrationFailed());
            }
        }

        if(!successfulMigration.isEmpty()){
            try {
                productRepository.deleteAll(successfulMigration);
            } catch (Exception e) {
                log.info(CANT_DELETE_MESSAGE + e.getMessage());
            }
        }

        if(!failedMigration.isEmpty()){
            try {
                productRepository.saveAll(failedMigration);
            } catch (Exception e) {
                log.info(CANT_SAVE_MESSAGE + e.getMessage());
            }
        }
    }
}
