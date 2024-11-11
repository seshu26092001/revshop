package com.Revshop.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int catid) {
        super("Category not found with ID: " + catid);
    }
}
