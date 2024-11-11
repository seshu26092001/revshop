package com.Revshop.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(int cartId) {
        super("Cart item not found with id: " + cartId);
    }
}
