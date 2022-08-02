package com.example.ActiveMQ.service;

import com.example.ActiveMQ.activeMq.ActiveMQProducer;
import com.example.ActiveMQ.converter.ProductConverter;
import com.example.ActiveMQ.dto.ProductDto;
import com.example.ActiveMQ.entity.ProductEntity;
import com.example.ActiveMQ.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.activemq.plugin.DiscardingDLQBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.activemq.plugin.ForcePersistencyModeBroker.log;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final String SENT_PRODUCTS_MESSAGE = "Products number sent to migrate: ";
    private final ActiveMQProducer activeMQProducer;
    private final ProductConverter productConverter;

    public String sendProductsToMigrate(){
        int itemsCount;
        List<ProductEntity> productEntities;
        Set<ProductDto> productDtos;

        productEntities = productRepository.findByMigrated(Boolean.FALSE);
        itemsCount = productEntities.size();

        productDtos = productEntities.stream()
                .map(productConverter::convertEntityToDto)
                .collect(Collectors.toSet());

        if(!productDtos.isEmpty()){
            activeMQProducer.sendNonMigratedItems(productDtos);
        }

        return SENT_PRODUCTS_MESSAGE + itemsCount;
    }

    public void migrateIncomingProducts(Set<ProductDto> items){
        items.stream()
                .forEach((ProductDto item ) -> migrateProduct(item.getId()));
    }

    private void migrateProduct(Long id){
        ProductEntity product;

        product = productRepository.getReferenceById(id);
        if (!product.getMigrated()){
            product.setMigrated(Boolean.TRUE);
            log.info(product.getName() + " was migrated");
        }
    }
}
