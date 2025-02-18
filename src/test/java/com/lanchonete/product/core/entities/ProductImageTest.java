package com.lanchonete.product.core.entities;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProductImageTest {

    @Test
    void shouldCreateProductImageSuccessfully() {
        UUID imageId = UUID.randomUUID();
        Product product = new Product(UUID.randomUUID(), "Bebidas", "Coca-Cola", null, "Refrigerante");
        String name = "product_image.png";
        String bucketUrl = "https://storage.example.com/product_image.png";

        ProductImage productImage = new ProductImage(imageId, product, name, bucketUrl);

        assertNotNull(productImage, "A instância do ProductImage não deve ser nula");
        assertEquals(imageId, productImage.getProductImageId(), "O ID da imagem deve corresponder");
        assertEquals(product, productImage.getProductId(), "O produto associado deve corresponder");
        assertEquals(name, productImage.getName(), "O nome da imagem deve corresponder");
        assertEquals(bucketUrl, productImage.getBucketUrl(), "A URL do bucket deve corresponder");
    }
}