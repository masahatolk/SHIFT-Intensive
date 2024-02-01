package ru.cft.template.exception;

public class UserIncorrectPassword extends Exception{
    public UserIncorrectPassword (String message){
        super(message);
    }
}
