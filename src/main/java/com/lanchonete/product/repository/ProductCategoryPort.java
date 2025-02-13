package com.lanchonete.product.repository;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;

import java.util.UUID;

public interface ProductCategoryPort {
    ProductCategory findProductCategory(UUID productCategoryId);
}