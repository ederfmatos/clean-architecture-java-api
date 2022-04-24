package com.ederfmatos.spring.api.config.handler;

record ErrorResponse(String error) {
    public static ErrorResponse of(Exception exception) {
        return new ErrorResponse(exception.getMessage());
    }
}