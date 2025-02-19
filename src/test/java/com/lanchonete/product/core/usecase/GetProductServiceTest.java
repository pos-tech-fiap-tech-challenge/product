package com.lanchonete.product.core.usecase;

import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.repository.ProductPort;
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
class GetProductServiceTest {

    @Mock
    private ProductPort productPort;

    @InjectMocks
    private GetProductService getProductService;

    private UUID productId;
    private Product product;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product(); // Supondo que Product tenha um construtor padrão
    }

    @Test
    void shouldReturnProductWhenFound() {
        when(productPort.getById(productId)).thenReturn(product);

        Product result = getProductService.getProduct(productId.toString());

        assertNotNull(result);
        assertEquals(product, result);
        verify(productPort, times(1)).getById(productId);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForInvalidUUID() {
        String invalidId = "invalid-uuid";

        assertThrows(IllegalArgumentException.class, () ->
                getProductService.getProduct(invalidId)
        );
    }
}