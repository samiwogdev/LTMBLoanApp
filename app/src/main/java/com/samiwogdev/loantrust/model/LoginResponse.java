package com.samiwogdev.loantrust.model;

public class LoginResponse {
    private boolean error;
    private String message;
    private Customer customer;

    public LoginResponse(boolean error, String message, Customer customer) {
        this.error = error;
        this.message = message;
        this.customer = customer;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
