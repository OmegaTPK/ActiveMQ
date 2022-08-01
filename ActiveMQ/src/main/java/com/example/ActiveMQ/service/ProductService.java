package com.example.ActiveMQ.service;

import com.example.ActiveMQ.activeMq.ActiveMQProducer;
import com.example.ActiveMQ.converter.ProductConverter;
import com.example.ActiveMQ.dto.ProductDto;
import com.example.ActiveMQ.entity.ProductEntity;
import com.example.ActiveMQ.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final String SENT_ITEMS_MESSAGE = "Items number sent to migrate: ";
    private final ActiveMQProducer activeMQProducer;
    private final ProductConverter productConverter;

    public String sendItems(){
        Integer itemsCount;
        List<ProductEntity> productEntities;
        Set<ProductDto> productDtos;

        productEntities = productRepository.findByMigrated(Boolean.TRUE);
        itemsCount = productEntities.size();

        productDtos = productEntities.stream()
                .map(productConverter::convertEntityToDto)
                .collect(Collectors.toSet());

        activeMQProducer.sendNonMigratedItems(productDtos);

        return SENT_ITEMS_MESSAGE + itemsCount.toString();
    }

    public void processIncomingItem(List<ProductDto> items){

    }
}
