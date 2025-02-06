package com.lanchonete.product.core.exceptions;

public class NotFoundProductIdException extends RuntimeException {
    public NotFoundProductIdException(String message) {
        super(message);
    }
}
