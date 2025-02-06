package com.lanchonete.product.core.usecase.interfaces;

import com.lanchonete.product.controller.DTO.ProductRequest;

public interface SaveProductUseCase {
    void saveProduct(ProductRequest product);
}