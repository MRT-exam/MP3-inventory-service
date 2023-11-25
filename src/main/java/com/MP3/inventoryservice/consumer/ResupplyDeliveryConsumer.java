package com.MP3.inventoryservice.consumer;

import com.MP3.inventoryservice.Event.OrderPlacedEvent;
import com.MP3.inventoryservice.Event.ResupplyDeliveryEvent;
import com.MP3.inventoryservice.dto.OrderedProductDto;
import com.MP3.inventoryservice.dto.ResupplyProductDto;
import com.MP3.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResupplyDeliveryConsumer {
    private final ProductService productService;

    @KafkaListener(topics = "resupplyDeliveryTopic")
    public void consume(ResupplyDeliveryEvent event) {
        // Call productService and update quantityInStock
        List<ResupplyProductDto> resupplyProductDtos = event.getResuppliedProductDtos();
        for (int i = 0; i < resupplyProductDtos.size(); i++) {
            int productId = resupplyProductDtos.get(i).getId();
            int resupplyQuantity = resupplyProductDtos.get(i).getResupplyQuantity();
            productService.restockProduct(productId, resupplyQuantity);
        }
    }
}
