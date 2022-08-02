package com.example.ActiveMQ.activeMq;

import com.example.ActiveMQ.dto.ProductDto;
import com.example.ActiveMQ.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.HashSet;
import java.util.Set;

import static org.apache.activemq.plugin.ForcePersistencyModeBroker.log;

@Component
@AllArgsConstructor
public class ActiveMQConsumer {

    private ProductService productService;
    private ObjectMapper objectMapper;

    @SneakyThrows
    @JmsListener(destination = "ProductMigration")
    public void processMessages(Message message) {
        String messageData = null;
        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            messageData = textMessage.getText();
        }
        Set<ProductDto> productDtos = objectMapper.readValue(messageData, new TypeReference<Set<ProductDto>>(){});
        productService.migrateIncomingProducts(productDtos);
    }

}
