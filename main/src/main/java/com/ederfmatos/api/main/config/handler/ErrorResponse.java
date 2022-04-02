package com.ederfmatos.api.main.config.handler;

record ErrorResponse(String error) {
    public static ErrorResponse of(Exception exception) {
        return new ErrorResponse(exception.getMessage());
    }
}