package com.MP3.inventoryservice.producer;

import com.MP3.inventoryservice.Event.ResupplyRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class ResupplyRequestedProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void produce(ResupplyRequestedEvent event) {
        kafkaTemplate.send("inventoryResupplyTopic", event);
    }
}

