package com.lanchonete.product.core.usecase;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.exceptions.InvalidCategoryException;
import com.lanchonete.product.core.usecase.interfaces.SaveProductUseCase;
import com.lanchonete.product.repository.ProductCategoryPort;
import com.lanchonete.product.repository.ProductPort;
import com.lanchonete.product.utils.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class SaveProductService implements SaveProductUseCase {


    private final ProductPort productPort;

    private final ProductCategoryPort productCategoryPort;

    private final ProductMapper productMapper;

    public SaveProductService(ProductPort productPort, ProductCategoryPort productCategoryPort, ProductMapper productMapper) {
        this.productPort = productPort;
        this.productCategoryPort = productCategoryPort;
        this.productMapper = productMapper;
    }

    @Override
    public void saveProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        productPort.saveOrUpdate(product);
    }
}