package com.example.ActiveMQ.activeMq;

import com.example.ActiveMQ.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class ActiveMQProducer {

    private JmsTemplate jmsTemplate;

    public void sendNonMigratedItems(Set<ProductDto> list){
        jmsTemplate.convertAndSend("ProductMigration", list);
    }
}
