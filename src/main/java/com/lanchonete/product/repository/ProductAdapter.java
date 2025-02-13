package com.lanchonete.product.repository;


import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.exceptions.InvalidCategoryException;
import com.lanchonete.product.core.exceptions.ProductCategoryNotFoundException;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import com.lanchonete.product.repository.entities.ProductEntity;
import com.lanchonete.product.utils.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository;


    @Transactional
    @Override
    public void removeProduct(UUID productId) {
        Optional<ProductEntity> entity = productRepository.findById(productId);
        if (entity.isPresent()) {
            productRepository.deleteByProductId(productId);
        } else {
            System.out.println("Entity not found");
        }

    }

    @Override
    public Boolean findById(UUID id) {
        return productRepository.findById(id).isPresent();
    }

    @Override
    public Product getById(UUID productId) {
        return productMapper.toProduct(productRepository.findById(productId).get());
    }


    @Override
    public Product saveOrUpdate(Product product) {

        ProductEntity productEntity = productMapper.toEntity(product);

        if (product.getProductId() != null) {
            productEntity.setProductId(product.getProductId());
        }
        productEntity.setCategory(getProductCategory(product.getCategory()));
        return productMapper.toProduct(productRepository.save(productEntity));
    }

    private ProductCategoryEntity getProductCategory(String category) {
        return productCategoryRepository.findByDescription(category).orElseThrow(
                () -> new ProductCategoryNotFoundException(category)
        );
    }


}
