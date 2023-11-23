package com.MP3.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;
    private int quantityInStock;
    private int price;
    private String description;
}
