package com.MP3.inventoryservice.producer;

import com.MP3.inventoryservice.Event.ResupplyRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ResupplyRequestedProducer {
    private KafkaTemplate<String, ResupplyRequestedEvent> kafkaTemplate;

    public void produce(ResupplyRequestedEvent event) {
        kafkaTemplate.send("inventoryRestockTopic", event);
    }
}

