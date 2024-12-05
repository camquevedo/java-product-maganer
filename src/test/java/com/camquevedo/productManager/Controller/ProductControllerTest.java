package com.camquevedo.productManager.Controller;

import com.camquevedo.productManager.models.v1.Product;
import com.camquevedo.productManager.service.v1.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
        String productJson = """
        {
            "name": "Doritos",
            "description": "Doritos locos",
            "price": 321,
            "stock": 12
        }""";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Doritos"))
                .andExpect(jsonPath("$.description").value("Doritos locos"))
                .andExpect(jsonPath("$.price").value(321))
                .andExpect(jsonPath("$.stock").value(12));
    }

    @Test
    public void not_create_product_test() throws Exception {
        String productJson = """
        {
            "name": "Doritos",
            "description": "Doritos locos",
            "price": "321usd",
            "stock": 12
        }""";

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void read_product_by_id_test() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Doritos");
        newProduct.setDescription("picantes y duros");
        newProduct.setPrice(BigDecimal.valueOf(123));
        newProduct.setStock(1);

        Product saved = service.create(newProduct);

        mockMvc.perform(get("/api/products/" + saved.getId()))
                .andExpect(status().isOk())  // Status should be OK (200)
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.name").value("Doritos"))
                .andExpect(jsonPath("$.description").value("picantes y duros"))
                .andExpect(jsonPath("$.price").value(123))
                .andExpect(jsonPath("$.stock").value(1));
    }

    @Test
    public void read_all_products_test() throws Exception {
        mockMvc.perform(get("/api/products")).andExpect(status().isOk());
    }

    @Test
    public void update_product_test() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Chicharrones");
        newProduct.setDescription("picantes y duro");
        newProduct.setPrice(BigDecimal.valueOf(123));
        newProduct.setStock(0);

        Product saved = service.create(newProduct);

        String productJson = """
        {
            "name": "Doritos",
            "description": "Doritos locos",
            "price": 321,
            "stock": 12
        }""";

        mockMvc.perform(put("/api/products/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk());
    }

    @Test
    public void update_inexistent_product_test() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Chicharrones");
        newProduct.setDescription("picantes y duro");
        newProduct.setPrice(BigDecimal.valueOf(123));
        newProduct.setStock(0);

        Product saved = service.create(newProduct);

        String productJson = """
        {
            "name": "Doritos",
            "description": "Doritos locos",
            "price": 321,
            "stock": 12
        }""";

        mockMvc.perform(put("/api/products/" + (saved.getId() +1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_product_test() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Doritos");
        newProduct.setDescription("picantes y duro");
        newProduct.setPrice(BigDecimal.valueOf(123));
        newProduct.setStock(0);

        Product saved = service.create(newProduct);

        mockMvc.perform(delete("/api/products/" + saved.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_not_existent_product_test() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Doritos");
        newProduct.setDescription("picantes y duro");
        newProduct.setPrice(BigDecimal.valueOf(123));
        newProduct.setStock(0);

        Product saved = service.create(newProduct);

        mockMvc.perform(delete("/api/products/" + (saved.getId() +1)))
                .andExpect(status().isNoContent());
    }
}
