package com.MP3.inventoryservice.service;

import com.MP3.inventoryservice.dto.InventoryResponse;
import com.MP3.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> productName){
        return inventoryRepository.findByProductNameIn(productName).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .productName(inventory.getProductName())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
