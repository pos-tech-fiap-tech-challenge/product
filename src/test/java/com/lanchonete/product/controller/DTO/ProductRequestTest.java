package com.lanchonete.product.controller.DTO;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductRequestTest {

    @Test
    void shouldCreateProductRequestSuccessfully() {
        UUID productId = UUID.randomUUID();
        String category = "Bebidas";
        String name = "Coca-Cola";
        BigDecimal price = new BigDecimal("5.99");
        String description = "Refrigerante de cola";

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId(productId);
        productRequest.setCategory(category);
        productRequest.setName(name);
        productRequest.setPrice(price);
        productRequest.setDescription(description);

        assertNotNull(productRequest, "A instância de ProductRequest não deve ser nula");
        assertEquals(productId, productRequest.getProductId(), "O ID do produto deve corresponder");
        assertEquals(category, productRequest.getCategory(), "A categoria deve corresponder");
        assertEquals(name, productRequest.getName(), "O nome deve corresponder");
        assertEquals(price, productRequest.getPrice(), "O preço deve corresponder");
        assertEquals(description, productRequest.getDescription(), "A descrição deve corresponder");
    }
}
