package com.MP3.inventoryservice.repository;

import com.MP3.inventoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameIn(List<String> productName);
}
