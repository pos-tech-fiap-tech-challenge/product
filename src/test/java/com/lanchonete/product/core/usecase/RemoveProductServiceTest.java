package com.lanchonete.product.core.usecase;

import com.lanchonete.product.core.exceptions.NotFoundProductException;
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
class RemoveProductServiceTest {

    @Mock
    private ProductPort productPort;

    @InjectMocks
    private RemoveProductService removeProductService;

    private UUID productId;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
    }

    @Test
    void shouldRemoveProductWhenExists() {
        when(productPort.findById(productId)).thenReturn(true);

        assertDoesNotThrow(() -> removeProductService.removeProduct(productId));
        verify(productPort, times(1)).removeProduct(productId);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productPort.findById(productId)).thenReturn(false);

        NotFoundProductException exception = assertThrows(NotFoundProductException.class, () ->
                removeProductService.removeProduct(productId)
        );

        assertEquals("This product does not exist Id: " + productId, exception.getMessage());
        verify(productPort, never()).removeProduct(productId);
    }
}