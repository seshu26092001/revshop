package com.Revshop.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String userid) {
        super("Customer not found with User ID: " + userid);
    }
}
