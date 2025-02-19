package com.lanchonete.product.core.entities;

import com.lanchonete.product.controller.group.OnUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateProductSuccessfully() {
        Product product = new Product(UUID.randomUUID(), "Bebidas", "Coca-Cola", new BigDecimal("5.99"), "Refrigerante");
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertTrue(violations.isEmpty(), "O produto deve ser válido");
    }

    @Test
    void shouldFailValidationWhenNameIsNull() {
        Product product = new Product(UUID.randomUUID(), "Bebidas", null, new BigDecimal("5.99"), "Refrigerante");
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertFalse(violations.isEmpty(), "Deve falhar ao validar um produto sem nome");
    }

    @Test
    void shouldFailValidationWhenProductIdIsNullOnUpdate() {
        Product product = new Product(null, "Bebidas", "Coca-Cola", new BigDecimal("5.99"), "Refrigerante");
        Set<ConstraintViolation<Product>> violations = validator.validate(product, OnUpdate.class);
        assertFalse(violations.isEmpty(), "Deve falhar ao validar um produto sem ID na atualização");
    }
}
