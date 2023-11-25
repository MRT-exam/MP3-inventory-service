package com.MP3.inventoryservice.service;

import com.MP3.inventoryservice.Event.ResupplyRequestedEvent;
import com.MP3.inventoryservice.dto.InventoryResponse;
import com.MP3.inventoryservice.dto.OrderedProductDto;
import com.MP3.inventoryservice.dto.ResupplyProductDto;
import com.MP3.inventoryservice.model.Product;
import com.MP3.inventoryservice.producer.ResupplyRequestedProducer;
import com.MP3.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ResupplyRequestedProducer resupplyRequestedProducer;

    @Transactional(readOnly = true)
    public List<InventoryResponse> quantityInStock(List<String> productNames){
        return productRepository.findByProductNameIn(productNames).stream()
                .map(product ->
                    InventoryResponse.builder()
                            .productName(product.getProductName())
                            .quantityInStock(product.getQuantityInStock())
                            .build()
                ).toList();
    }

    // TODO: Refactor the updating of quantity and the check for resupply
    public void updateInventoryStock(List<OrderedProductDto> orderedProductDtos) {
        // Retrieve ids of ordered products
        List<Integer> orderedProductsIds = orderedProductDtos
                .stream()
                .map(OrderedProductDto::getId)
                .toList();
        // Retrieve quantities of each product ordered
        List<Integer> quantitiesOrdered = orderedProductDtos
                .stream()
                .map(OrderedProductDto::getQuantity)
                .toList();

        // Retrieve products in db that have been ordered
        List<Product> productsToBeUpdated = productRepository.findAllById(orderedProductsIds);
        // Update the quantities in stock for each of the products ordered
        for (int i = 0; i < productsToBeUpdated.size(); i++) {
            Product currentProduct = productsToBeUpdated.get(i);
            currentProduct.setQuantityInStock(
                    currentProduct.getQuantityInStock()-quantitiesOrdered.get(i)
            );
        }
        List<Product> updatedProducts = productRepository.saveAll(productsToBeUpdated);
        // Add the products to be restocked to the ResupplyRequestedEvent
        ResupplyRequestedEvent resupplyRequestedEvent = new ResupplyRequestedEvent();
        updatedProducts
                .stream()
                .filter(product -> product.getQuantityInStock() < 20)
                .map(this::mapToDto)
                .forEachOrdered(resupplyRequestedEvent.getProductsToResupply()::add);
        resupplyRequestedProducer.produce(resupplyRequestedEvent);
    }

    private ResupplyProductDto mapToDto(Product product) {
        ResupplyProductDto resupplyProductDto = new ResupplyProductDto();
        resupplyProductDto.setId(product.getId());
        resupplyProductDto.setProductName(product.getProductName());
        // Constant resupply of 20
        resupplyProductDto.setQuantityToRestock(20);
        return resupplyProductDto;
    }
}
