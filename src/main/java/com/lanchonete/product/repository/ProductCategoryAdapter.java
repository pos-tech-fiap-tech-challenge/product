package com.lanchonete.product.repository;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.utils.ProductCategoryMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductCategoryAdapter implements ProductCategoryPort {

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryAdapter(ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public ProductCategory findProductCategory(UUID productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).map(productCategoryMapper::toProductCategory).orElse(null);
    }

}
