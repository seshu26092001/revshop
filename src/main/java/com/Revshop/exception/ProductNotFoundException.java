package com.Revshop.exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(int prodid) {
		super("Product not found with ID: " + prodid);
	}
}
