package com.example.ActiveMQ.activeMq;

import com.example.ActiveMQ.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.List;

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
        List<Long> productDtos = objectMapper.readValue(messageData, new TypeReference<List<Long>>(){});
        productService.migrateIncomingProducts(productDtos);
    }

}
