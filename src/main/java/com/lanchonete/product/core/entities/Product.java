package com.lanchonete.product.core.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lanchonete.product.controller.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {

    @NotNull(groups = OnUpdate.class, message = "CategoryId is required for update")
    private UUID productId;

    private String category;

    @NotNull(message = "name product required")
    private String name;

    private BigDecimal price;

    private String description;

    public Product(UUID productId, String s, String refrigerante, BigDecimal bigDecimal) {
    }
}