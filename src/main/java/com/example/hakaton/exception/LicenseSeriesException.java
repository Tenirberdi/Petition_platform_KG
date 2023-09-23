package com.example.hakaton.exception;

import org.springframework.http.HttpStatus;

public enum LicenseSeriesException implements CustomError {

    LICENSE_SERIES_EXCEPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "license series с таким id в бд не найден", "Серия лицензии не найден", "Извините, указанный председатель не существует в системе. Пожалуйста, проверьте правильность введенных данных или обратитесь в тех. поддержку за дополнительной информацией."),

    ;
    String reason;
    String title;
    String message;
    HttpStatus status;

    LicenseSeriesException(HttpStatus status, String reason, String title, String message) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.title = title;

    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getReason() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}
