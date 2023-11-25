package com.MP3.inventoryservice.consumer;

import com.MP3.inventoryservice.Event.OrderPlacedEvent;
import com.MP3.inventoryservice.dto.OrderedProductDto;
import com.MP3.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderPlacedConsumer {

    private final ProductService productService;

    @KafkaListener(topics = "inventoryUpdateTopic")
    public void consume(OrderPlacedEvent event) {
        // Call ProductService and update quantities in stock in DB
        List<OrderedProductDto> orderedProducts = event.getOrderedProductDtos();
        productService.handleOrderedProducts(orderedProducts);
    }
}
