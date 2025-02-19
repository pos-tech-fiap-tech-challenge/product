package com.lanchonete.product.core.usecase;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.repository.ProductCategoryPort;
import com.lanchonete.product.repository.ProductPort;
import com.lanchonete.product.utils.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveProductServiceTest {

    @Mock
    private ProductPort productPort;

    @Mock
    private ProductCategoryPort productCategoryPort;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private SaveProductService saveProductService;

    private ProductRequest productRequest;
    private Product product;

    @BeforeEach
    void setUp() {
        productRequest = new ProductRequest(); // Supondo que tenha um construtor padrão
        product = new Product(); // Supondo que tenha um construtor padrão
    }

    @Test
    void shouldSaveProductSuccessfully() {
        when(productMapper.toProduct(productRequest)).thenReturn(product);

        saveProductService.saveProduct(productRequest);

        verify(productPort, times(1)).saveOrUpdate(product);
    }
}