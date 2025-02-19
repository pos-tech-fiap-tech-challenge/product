package com.lanchonete.product.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        productCategory = new ProductCategory(UUID.randomUUID(), "Bebidas", null);
    }

    @Test
    void shouldAddProductSuccessfully() {
        Product product = new Product(UUID.randomUUID(), "Bebidas", "Coca-Cola", null, "Refrigerante");
        productCategory.addProducts(product);

        assertNotNull(productCategory.getProducts(), "A lista de produtos não deve ser nula");
        assertEquals(1, productCategory.getProducts().size(), "A lista de produtos deve conter um item");
        assertTrue(productCategory.getProducts().contains(product), "O produto deve estar na lista");
    }

    @Test
    void shouldNotAddDuplicateProducts() {
        Product product = new Product(UUID.randomUUID(), "Bebidas", "Coca-Cola", null, "Refrigerante");
        productCategory.addProducts(product);
        productCategory.addProducts(product);

        assertEquals(1, productCategory.getProducts().size(), "A lista de produtos não deve conter itens duplicados");
    }
}
