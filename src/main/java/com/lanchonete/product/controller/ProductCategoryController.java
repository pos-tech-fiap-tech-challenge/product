package com.lanchonete.product.controller;

import com.lanchonete.product.core.entities.ProductCategory;
import com.lanchonete.product.core.usecase.interfaces.ProductCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ProductCategoryController.BASE_URL)
public class ProductCategoryController {

    public static final String BASE_URL = "/products/categories";

    private ProductCategoryUseCase productCategoryUseCase;

    public ProductCategoryController(ProductCategoryUseCase productCategoryUseCase) {
        this.productCategoryUseCase = productCategoryUseCase;
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> searchCategory(@PathVariable("categoryId") UUID categoryId) {
        ProductCategory productCategory = productCategoryUseCase.findProductCategory(categoryId);
        return ResponseEntity.ok(productCategory);
    }

}