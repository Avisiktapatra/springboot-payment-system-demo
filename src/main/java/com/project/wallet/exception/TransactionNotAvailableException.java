package com.project.wallet.exception;

public class TransactionNotAvailableException extends RuntimeException {

    private Integer id;

    public TransactionNotAvailableException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
