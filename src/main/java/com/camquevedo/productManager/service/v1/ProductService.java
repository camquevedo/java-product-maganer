package com.camquevedo.productManager.service.v1;

import com.camquevedo.productManager.models.v1.Product;
import com.camquevedo.productManager.repository.v1.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    public ResponseEntity<Product> update (Long id, Product updatedProduct) {

        Optional<Product> productResponse = repository.findById(id);
            if (productResponse.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Product currentProduct = productResponse.get();

            currentProduct.setName(updatedProduct.getName());
            currentProduct.setDescription(updatedProduct.getDescription());
            currentProduct.setPrice(updatedProduct.getPrice());
            currentProduct.setStock(updatedProduct.getStock());

            repository.save(currentProduct);
            return ResponseEntity.ok(updatedProduct);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }
}
