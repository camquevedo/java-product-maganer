package com.camquevedo.productManager.Status;

import com.camquevedo.productManager.models.v1.Product;
import com.camquevedo.productManager.repository.v1.ProductRepository;
import com.camquevedo.productManager.service.v1.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductManagerIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product(
                "Named product",
                "Test Product",
                BigDecimal.valueOf(1000),
                0
        );
        productService.create(newProduct);

        // Retrieve the product from the repository
        Product savedProduct = productRepository.findById(newProduct.getId()).orElse(null);

        // Assert that the product was saved correctly
        assertNotNull(savedProduct);
        assertEquals(newProduct.getName(), savedProduct.getName());
        assertEquals( newProduct.getPrice().intValue(), savedProduct.getPrice().intValue());

        // Assert that the product is saved correctly (e.g., using JPA assertions)
    }
}