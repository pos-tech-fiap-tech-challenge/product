package com.lanchonete.product.controller;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.usecase.interfaces.ProductCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductCategoryControllerTest {

    @Mock
    private ProductCategoryUseCase productCategoryUseCase;

    @InjectMocks
    private ProductCategoryController productCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProductCategoryWhenExists() {
        UUID categoryId = UUID.randomUUID();
        ProductCategory mockCategory = new ProductCategory(categoryId, "Bebidas", null);

        when(productCategoryUseCase.findProductCategory(categoryId)).thenReturn(mockCategory);

        ResponseEntity<ProductCategory> response = productCategoryController.searchCategory(categoryId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockCategory, response.getBody());
        verify(productCategoryUseCase, times(1)).findProductCategory(categoryId);
    }
}