package com.lanchonete.product.repository;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import com.lanchonete.product.utils.ProductCategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductCategoryAdapterTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    private ProductCategoryMapper productCategoryMapper;

    @InjectMocks
    private ProductCategoryAdapter productCategoryAdapter;

    private UUID categoryId;
    private ProductCategoryEntity categoryEntity;
    private ProductCategory category;

    @BeforeEach
    void setUp() {
        categoryId = UUID.randomUUID();
        categoryEntity = new ProductCategoryEntity();
        categoryEntity.setProductCategoryId(categoryId);
        categoryEntity.setDescription("Bebidas");

        category = new ProductCategory();
        category.setProductCategoryId(categoryId);
        category.setDescription("Bebidas");
    }

    @Test
    void shouldReturnProductCategoryWhenExists() {
        when(productCategoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(productCategoryMapper.toProductCategory(categoryEntity)).thenReturn(category);

        ProductCategory result = productCategoryAdapter.findProductCategory(categoryId);

        assertNotNull(result);
        assertEquals(categoryId, result.getProductCategoryId());
        assertEquals("Bebidas", result.getDescription());
    }

    @Test
    void shouldReturnNullWhenCategoryDoesNotExist() {
        when(productCategoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        ProductCategory result = productCategoryAdapter.findProductCategory(categoryId);

        assertNull(result);
    }
}