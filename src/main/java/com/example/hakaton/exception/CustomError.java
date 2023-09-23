package com.example.hakaton.exception;

import org.springframework.http.HttpStatus;

public interface CustomError {
    String getMessage();
    String getReason();
    String getTitle();
    HttpStatus getStatus();
}
