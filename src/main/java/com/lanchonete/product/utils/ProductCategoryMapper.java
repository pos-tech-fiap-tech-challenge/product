package com.lanchonete.product.utils;

import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import com.lanchonete.product.repository.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategory toProductCategory(ProductCategoryEntity productCategoryEntity);
}
