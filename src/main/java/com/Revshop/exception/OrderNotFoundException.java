package com.Revshop.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(int orderid) {
        super("Order not found with ID: " + orderid);
    }
}
