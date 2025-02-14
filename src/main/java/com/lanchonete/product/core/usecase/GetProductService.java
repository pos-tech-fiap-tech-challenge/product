package com.lanchonete.product.core.usecase;

import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.usecase.interfaces.GetProductUseCase;
import com.lanchonete.product.repository.ProductPort;
import com.lanchonete.product.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProductService implements GetProductUseCase {

    ProductPort productPort;

    @Override
    public Product getProduct(String id) {
        return productPort.getById(UUID.fromString(id));
    }
}
