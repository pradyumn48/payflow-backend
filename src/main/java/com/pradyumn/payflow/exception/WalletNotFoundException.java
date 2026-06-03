package com.pradyumn.payflow.exception;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String message){
        super(message);
    }
}