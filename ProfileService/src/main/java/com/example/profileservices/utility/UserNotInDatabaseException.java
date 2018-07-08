package com.example.profileservices.utility;

public class UserNotInDatabaseException extends Exception {

    public UserNotInDatabaseException(String message){
        super(message);
    }
}
