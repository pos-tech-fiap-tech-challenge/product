package com.lanchonete.product.utils;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.repository.entities.ProductCategoryEntity;
import com.lanchonete.product.repository.entities.ProductEntity;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequest productRequest);

    @Mapping(source = "entity.category.description", target = "category")
    Product toProduct(ProductEntity entity);

    @Mapping(target = "category", ignore = true)
    ProductEntity toEntity(Product product);

}
