package com.lanchonete.product.repository;

import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, UUID> {
    Optional<ProductCategoryEntity> findByDescription(String categoryDescription);
}