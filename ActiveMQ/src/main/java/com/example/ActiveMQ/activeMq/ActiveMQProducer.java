package com.example.ActiveMQ.activeMq;

import com.example.ActiveMQ.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ActiveMQProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendNonMigratedItems(Set<ProductDto> list){
        jmsTemplate.convertAndSend("ProductMigration", list);
    }
}
