package com.lanchonete.product.repository;

import com.lanchonete.product.core.entities.Product;

import java.util.UUID;

public interface ProductPort {

    Product saveOrUpdate(Product product);
    void removeProduct(UUID productId);
    Boolean findById(UUID productId);
    Product getById(UUID productId);

}