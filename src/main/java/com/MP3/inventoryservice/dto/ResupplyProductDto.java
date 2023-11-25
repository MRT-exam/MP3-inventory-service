package com.MP3.inventoryservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResupplyProductDto {
    private int id;
    private String productName;
    private int resupplyQuantity;
}
