package com.lanchonete.product.core.usecase.interfaces;

import com.lanchonete.product.core.entities.Product;

public interface GetProductUseCase {
    Product getProduct(String id);
}
