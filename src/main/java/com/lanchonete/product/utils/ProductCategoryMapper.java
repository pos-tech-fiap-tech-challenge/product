package com.lanchonete.product.utils;

import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import com.lanchonete.product.repository.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface ProductCategoryMapper {

    @Mapping(source = "products", target = "products")
    ProductCategory toProductCategory(ProductCategoryEntity entity);

    @Mapping(target = "products", ignore = true)
    ProductCategoryEntity toEntity(ProductCategory productCategory);
}

