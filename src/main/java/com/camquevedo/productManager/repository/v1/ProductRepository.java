package com.camquevedo.productManager.repository.v1;

import com.camquevedo.productManager.models.v1.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
