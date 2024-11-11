package com.Revshop.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String catName) {
        super("Category with name '" + catName + "' already exists.");
    }
}
