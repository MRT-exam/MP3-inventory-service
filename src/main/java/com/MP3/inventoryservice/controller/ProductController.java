package com.MP3.inventoryservice.controller;

import com.MP3.inventoryservice.dto.InventoryResponse;
import com.MP3.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> quantityInStock(@RequestParam List<String> productNames){
        return productService.quantityInStock(productNames);
    }

}
