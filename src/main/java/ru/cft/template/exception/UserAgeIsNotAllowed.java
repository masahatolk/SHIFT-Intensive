package ru.cft.template.exception;

public class UserAgeIsNotAllowed extends Exception{
    public UserAgeIsNotAllowed (String message){
        super(message);
    }
}
