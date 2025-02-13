package com.lanchonete.product.controller;

import com.lanchonete.product.controller.DTO.ProductRequest;
import com.lanchonete.product.controller.group.OnCreate;
import com.lanchonete.product.controller.group.OnUpdate;
import com.lanchonete.product.core.entities.Product;
import com.lanchonete.product.core.usecase.interfaces.RemoveProductUseCase;
import com.lanchonete.product.core.usecase.interfaces.SaveProductUseCase;
import com.lanchonete.product.core.usecase.interfaces.UpdateProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ProductController.BASE_URL)

public class ProductController {

    public static final String BASE_URL = "/products";

    private final SaveProductUseCase saveProductUseCase;

    private final UpdateProductUseCase updateProductUseCase;

    private final RemoveProductUseCase removeProductUseCase;

    public ProductController(SaveProductUseCase saveProductUseCase, UpdateProductUseCase updateProductUseCase, RemoveProductUseCase removeProductUseCase) {
        this.saveProductUseCase = saveProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.removeProductUseCase = removeProductUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Validated(OnCreate.class) @RequestBody ProductRequest productRequest) {
        saveProductUseCase.saveProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Validated(OnUpdate.class) @RequestBody ProductRequest productRequest) {
        Product product = updateProductUseCase.update(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> removeProduct(@PathVariable("productId") UUID productId) {
        removeProductUseCase.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}