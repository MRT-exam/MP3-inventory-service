package com.MP3.inventoryservice.service;

import com.MP3.inventoryservice.dto.InventoryResponse;
import com.MP3.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> quantityInStock(List<String> products){
        return productRepository.findByProductNameIn(products).stream()
                .map(product ->
                    InventoryResponse.builder()
                            .productName(product.getProductName())
                            .quantityInStock(product.getQuantityInStock())
                            .build()
                ).toList();
    }
}
