package com.gdpr.controller;

public class DateOfBirthNotCompatibleException extends RuntimeException {

    public DateOfBirthNotCompatibleException(String message){
        super(message);
    }
}
