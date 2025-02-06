package com.lanchonete.product.core.usecase.interfaces;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;

public interface UpdateProductUseCase {
    Product update(ProductRequest productRequest);
}
