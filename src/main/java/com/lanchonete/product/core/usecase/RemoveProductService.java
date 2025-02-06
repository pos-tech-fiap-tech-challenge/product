package com.lanchonete.product.core.usecase;

import com.lanchonete.product.core.exceptions.NotFoundProductException;
import com.lanchonete.product.core.usecase.interfaces.RemoveProductUseCase;
import com.lanchonete.product.repository.ProductPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveProductService implements RemoveProductUseCase {

    private final ProductPort productPort;

    public RemoveProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @Override
    public void removeProduct(UUID productId) {
        notFoundProduct(productId);
        productPort.removeProduct(productId);
    }

    private void notFoundProduct(UUID productId) {
        if (!productPort.findById(productId)) {
            throw new NotFoundProductException("This product does not exist Id: " + productId);
        }
    }
}
