package com.example.demo_DSA.error;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id) {
        super("No se puede encontrar el producto con el ID: " + id);
    }
}
