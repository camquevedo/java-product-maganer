package com.camquevedo.productManager.service.v1;

import com.camquevedo.productManager.models.v1.Product;
import com.camquevedo.productManager.repository.v1.ProductRepository;
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

    public Product update (Long id, Product updatedProduct) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setPrice(updatedProduct.getPrice());
                    product.setStock(updatedProduct.getStock());

                    return repository.save(product);
                }).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }
}
