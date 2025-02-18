package com.lanchonete.product.core.usecase;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.exceptions.NotFoundProductException;
import com.lanchonete.product.core.usecase.interfaces.GetProductUseCase;
import com.lanchonete.product.repository.ProductPort;
import com.lanchonete.product.utils.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductServiceTest {

    @Mock
    private GetProductUseCase getProductUseCase;

    @Mock
    private ProductPort productPort;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private UpdateProductService updateProductService;

    private UUID productId;
    private Product product;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product(productId, "Coca-Cola", "Refrigerante", new BigDecimal("5.99"));

        productRequest = new ProductRequest();
        productRequest.setProductId(productId);
        productRequest.setName("Pepsi");
        productRequest.setDescription("Refrigerante de Cola");
        productRequest.setPrice(new BigDecimal("4.99"));
    }

    @Test
    void shouldUpdateProductSuccessfully() {
        when(getProductUseCase.getProduct(productId.toString())).thenReturn(product);
        when(productPort.saveOrUpdate(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product updatedProduct = updateProductService.update(productRequest);

        assertNotNull(updatedProduct);
        assertEquals("Pepsi", updatedProduct.getName());
        assertEquals("Refrigerante de Cola", updatedProduct.getDescription());
        assertEquals(new BigDecimal("4.99"), updatedProduct.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(getProductUseCase.getProduct(productId.toString())).thenReturn(null);

        NotFoundProductException exception = assertThrows(NotFoundProductException.class, () ->
                updateProductService.update(productRequest));

        assertEquals("Item with Id: " + productId + " not found", exception.getMessage());
    }
}
