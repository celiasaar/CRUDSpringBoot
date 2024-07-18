package com.example.demo_DSA.error;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {
        super("No se puede encontrar la categoría con el ID: " + id);
    }
}
