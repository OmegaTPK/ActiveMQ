package com.example.ActiveMQ.activeMq;

import com.example.ActiveMQ.dto.ProductDto;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActiveMQProducer {
    private JmsTemplate jmsTemplate;

    public void sendNonMigratedItems(Set<ProductDto> list){
        jmsTemplate.convertAndSend("ProductMigration", list);
    }
}
