package com.lanchonete.product.core.usecase;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.exceptions.ProductCategoryNotFoundException;
import com.lanchonete.product.repository.ProductCategoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductCategoryServiceTest {

    @Mock
    private ProductCategoryPort productCategoryPort;

    @InjectMocks
    private GetProductCategoryService getProductCategoryService;

    private UUID categoryId;
    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        categoryId = UUID.randomUUID();
        productCategory = new ProductCategory(); // Supondo que tenha um construtor padrão ou use um builder
    }

    @Test
    void shouldReturnProductCategoryWhenFound() {
        when(productCategoryPort.findProductCategory(categoryId)).thenReturn(productCategory);

        ProductCategory result = getProductCategoryService.findProductCategory(categoryId);

        assertNotNull(result);
        assertEquals(productCategory, result);
        verify(productCategoryPort, times(1)).findProductCategory(categoryId);
    }

    @Test
    void shouldThrowExceptionWhenProductCategoryNotFound() {
        when(productCategoryPort.findProductCategory(categoryId)).thenReturn(null);

        assertThrows(ProductCategoryNotFoundException.class, () ->
                getProductCategoryService.findProductCategory(categoryId)
        );

        verify(productCategoryPort, times(1)).findProductCategory(categoryId);
    }
}
