package com.example.ActiveMQ.converter;

import com.example.ActiveMQ.dto.ProductDto;
import com.example.ActiveMQ.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductDto convertEntityToDto (ProductEntity entity){
        return new ProductDto(entity.getId());
    }

}
