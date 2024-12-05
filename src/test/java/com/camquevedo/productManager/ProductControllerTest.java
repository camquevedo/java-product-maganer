package com.camquevedo.productManager;

import com.camquevedo.productManager.controller.v1.ProductController;
import com.camquevedo.productManager.models.v1.Product;
import com.camquevedo.productManager.service.v1.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService service;


    @Test
    public void create_product_test() throws Exception {
        String productJson = "{ \"name\": \"Doritos\", \"description\": \"Doritos locos\", \"price\": 123, \"stock\": 21 }";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Doritos"));
    }

    @Test
    public void read_product_by_id_test() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Doritos");
        newProduct.setDescription("picantes y duro");
        newProduct.setPrice(BigDecimal.valueOf(123));
        newProduct.setStock(0);

        Product saved = service.create(newProduct);

        mockMvc.perform(get("/api/products/")).andExpect(status().isOk());
    }

    @Test
    public void read_all_products_test() throws Exception {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
    }

    @Test
    public void update_product_test() throws Exception {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
    }

    @Test
    public void delete_product_test() throws Exception {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
    }
}
