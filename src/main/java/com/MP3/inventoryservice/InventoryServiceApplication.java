package com.MP3.inventoryservice;

import com.MP3.inventoryservice.model.Product;
import com.MP3.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(ProductRepository inventoryRepository){
		return args -> {
			Product product = new Product();
			product.setProductName("samsung_13");
			product.setQuantityInStock(100);

			Product product1 = new Product();
			product1.setProductName("samsung_1");
			product1.setQuantityInStock(0);

			inventoryRepository.save(product);
			inventoryRepository.save(product1);
		};
	}
}
