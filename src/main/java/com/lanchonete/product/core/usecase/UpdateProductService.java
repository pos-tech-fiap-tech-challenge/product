package com.lanchonete.product.core.usecase;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.exceptions.NotFoundProductException;
import com.lanchonete.product.core.usecase.interfaces.GetProductUseCase;
import com.lanchonete.product.core.usecase.interfaces.UpdateProductUseCase;
import com.lanchonete.product.repository.ProductPort;
import com.lanchonete.product.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductService implements UpdateProductUseCase {

    GetProductUseCase getProductUseCase;
    ProductMapper productMapper;
    ProductPort productPort;

    @Override
    public Product update(ProductRequest productRequest) {
        Product product = getProductUseCase.getProduct(productRequest.getProductId().toString());
        if(product == null){
            throw new NotFoundProductException("Item with Id: "+ productRequest.getProductId()+" not found");
        }
        Product updatedProduct = updateProduct(product, productRequest);
        return productPort.saveOrUpdate(updatedProduct);
    }
    private Product updateProduct(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        return product;
    }
}
