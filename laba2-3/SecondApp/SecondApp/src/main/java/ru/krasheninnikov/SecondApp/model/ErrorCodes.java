package ru.krasheninnikov.SecondApp.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {

    EMPTY(""),
    VALIDATION_EXCEPTION("ValidationException"),
    UNSUPPORTED_EXCEPTION("UnSupportedException"),
    UNKNOWN_EXCEPTION("UnknownException");

    private final String name;

    ErrorCodes(String name){
        this.name=name;
    }

    @JsonValue
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}