package com.lanchonete.product.repository;

import com.lanchonete.product.repository.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Modifying
    @Query("DELETE FROM ProductEntity t WHERE t.productId = :pid")
    void deleteByProductId(@Param("pid") UUID id);
}