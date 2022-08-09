package com.example.ActiveMQ.activeMq;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ActiveMQProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendNonMigratedItems(Set<Long> list){
        jmsTemplate.convertAndSend("ProductMigration", list);
    }
}
