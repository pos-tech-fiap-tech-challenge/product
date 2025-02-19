package com.lanchonete.product.controller;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.usecase.interfaces.RemoveProductUseCase;
import com.lanchonete.product.core.usecase.interfaces.SaveProductUseCase;
import com.lanchonete.product.core.usecase.interfaces.UpdateProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private SaveProductUseCase saveProductUseCase;

    @Mock
    private UpdateProductUseCase updateProductUseCase;

    @Mock
    private RemoveProductUseCase removeProductUseCase;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_ShouldReturnCreatedStatus() {
        ProductRequest productRequest = new ProductRequest();

        ResponseEntity<Void> response = productController.createProduct(productRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(saveProductUseCase, times(1)).saveProduct(productRequest);
    }

    @Test
    void updateProduct_ShouldReturnCreatedStatus() {
        ProductRequest productRequest = new ProductRequest();
        Product product = new Product();
        when(updateProductUseCase.update(any(ProductRequest.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(productRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(updateProductUseCase, times(1)).update(productRequest);
    }

    @Test
    void removeProduct_ShouldReturnOkStatus() {
        UUID productId = UUID.randomUUID();

        ResponseEntity<Product> response = productController.removeProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(removeProductUseCase, times(1)).removeProduct(productId);
    }
}
