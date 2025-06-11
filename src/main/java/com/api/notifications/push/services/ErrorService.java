package com.api.notifications.push.services;

public class ErrorService extends Exception{
    public ErrorService(String message) {
        super(message);
    }
}
