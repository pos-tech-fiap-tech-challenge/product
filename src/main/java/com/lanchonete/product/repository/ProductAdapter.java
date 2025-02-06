package com.lanchonete.product.repository;


import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.repository.entities.ProductEntity;
import com.lanchonete.product.utils.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ProductAdapter implements ProductPort {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductMapper productMapper;


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
        return productMapper.toProduct(productRepository.save(productEntity));
    }


}
