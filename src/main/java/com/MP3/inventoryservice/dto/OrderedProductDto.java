package com.MP3.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDto {
    private int id;
    private String productName;
    private int quantity;
    private BigDecimal price;
}
