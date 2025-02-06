package com.lanchonete.product.core.usecase.interfaces;

import com.lanchonete.product.core.entities.ProductCategory;

import java.util.UUID;

public interface ProductCategoryUseCase {
    ProductCategory findProductCategory(UUID productCategoryId);

}
