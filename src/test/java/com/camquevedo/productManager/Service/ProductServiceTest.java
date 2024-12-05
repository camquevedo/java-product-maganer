package com.camquevedo.productManager.Service;

import com.camquevedo.productManager.models.v1.Product;
import com.camquevedo.productManager.repository.v1.ProductRepository;
import com.camquevedo.productManager.service.v1.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final ProductService service = new ProductService(repository);

    @Test
    public void create_test() {
        String testName = "test name";
        Product product = new Product();
        product.setName(testName);

        when(repository.save(product)).thenReturn(product);

        Product result = service.create(product);

        assertNotNull(result);
        assertEquals(testName, result.getName());
    }

    @Test
    public void read_all_test() {
        Product product = new Product();
//        product.setId(1L);
//        product.setName("Test product");

        when(repository.findAll()).thenReturn(List.of(product));

        List<Product> result = service.getAll();

        assertEquals(1, result.size());
    }

    @Test
    public void read_by_id_test() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test product");

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = service.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test product", result.get().getName());
    }

    @Test
    public void update_test() {
        // here we will test the product creation from controller
        double result = 1;
        assertEquals(result, 1);
    }

    @Test
    public void delete_test() {
        // here we will test the product creation from controller
        double result = 1;
        assertEquals(result, 1);
    }
}
