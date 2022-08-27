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
    private static final String SENT_PRODUCTS_MESSAGE = "Products number sent to migrate: ";
    private final ActiveMQProducer activeMQProducer;
    private final ProductConverter productConverter;

    public String sendProductsToMigrate(){
        List<ProductEntity> productEntities = productRepository.findByMigrated(Boolean.FALSE);
        Set<ProductDto> productDtoSet = productEntities.stream()
                .map(productConverter::convertEntityToDto)
                .collect(Collectors.toSet());
        if(!productDtoSet.isEmpty()){
            activeMQProducer.sendNonMigratedItems(productDtoSet);
        }
        return SENT_PRODUCTS_MESSAGE + productDtoSet.size();
    }

    public void migrateIncomingProducts(List<Long> productIdList){
        productRepository.setMigratedTrueByIdList(productIdList);
    }
}
