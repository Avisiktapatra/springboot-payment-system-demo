package com.project.wallet.exception;

public class InsufficientFundsException extends RuntimeException {

    private double amt;

    public InsufficientFundsException(double amt) {
        this.amt = amt;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

}
