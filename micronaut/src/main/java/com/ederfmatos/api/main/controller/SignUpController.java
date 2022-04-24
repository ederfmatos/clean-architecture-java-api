package com.ederfmatos.api.main.controller;

import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.handler.signup.SignUpRequest;
import com.ederfmatos.api.presentation.handler.signup.SignUpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/micronaut/signup")
public class SignUpController {

    private final SignUpHandler signUpHandler;

    @Inject
    public SignUpController(SignUpHandler signUpHandler) {
        this.signUpHandler = signUpHandler;
    }

    @Post
    public SignUpResponse signup(@Body SignUpRequest signUpRequest) {
        return this.signUpHandler.handle(signUpRequest);
    }

}
