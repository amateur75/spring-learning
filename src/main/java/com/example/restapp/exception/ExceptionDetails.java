package com.example.restapp.exception;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDetails {

    private Integer status;
    private String message;


    public ExceptionDetails(Integer status, String message) {

        super();

        this.status = status;
        this.message = message;
    }
}
