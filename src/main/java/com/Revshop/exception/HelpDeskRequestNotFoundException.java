package com.Revshop.exception;

public class HelpDeskRequestNotFoundException extends RuntimeException {
    public HelpDeskRequestNotFoundException(int requestId) {
        super("Help Desk request not found with id: " + requestId);
    }
}
