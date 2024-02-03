package ru.cft.template.exception;

public class ErrorException extends Exception{
    public ErrorException(String message){
        super("Произошла ошибка");
    }
}
