package com.lanchonete.product.core.usecase;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.exceptions.ProductCategoryNotFoundException;
import com.lanchonete.product.core.usecase.interfaces.ProductCategoryUseCase;
import com.lanchonete.product.repository.ProductCategoryPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service

public class GetProductCategoryService implements ProductCategoryUseCase {

    private final ProductCategoryPort productCategoryPort;

    public GetProductCategoryService(ProductCategoryPort productCategoryPort) {
        this.productCategoryPort = productCategoryPort;
    }

    @Override
    public ProductCategory findProductCategory(UUID productCategoryId) {
        ProductCategory productCategory = productCategoryPort.findProductCategory(productCategoryId);
        categoryNotFound(productCategory);
        return productCategory;
    }

    private void categoryNotFound(ProductCategory productCategory) {
        if(Objects.isNull(productCategory)){
            throw new ProductCategoryNotFoundException("Product Category not found");
        }
    }

}