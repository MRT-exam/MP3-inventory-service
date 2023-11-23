package com.MP3.inventoryservice.repository;

import com.MP3.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByProductNameIn(List<String> productName);
}
