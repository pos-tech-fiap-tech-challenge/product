package com.lanchonete.product.repository;

import com.lanchonete.product.core.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryPort {

    ProductCategory findProductCategory(UUID productCategoryId);
}