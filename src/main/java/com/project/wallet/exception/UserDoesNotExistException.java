package com.project.wallet.exception;

public class UserDoesNotExistException extends RuntimeException
{
    private String id;

    public UserDoesNotExistException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
