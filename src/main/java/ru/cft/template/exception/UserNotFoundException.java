package ru.cft.template.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException (String message){
        super(message);
    }
}
