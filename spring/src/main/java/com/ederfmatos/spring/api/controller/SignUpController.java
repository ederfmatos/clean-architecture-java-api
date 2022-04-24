package com.ederfmatos.spring.api.controller;

import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.handler.signup.SignUpRequest;
import com.ederfmatos.api.presentation.handler.signup.SignUpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/signup")
class SignUpController {

    private final SignUpHandler signUpHandler;

    SignUpController(SignUpHandler signUpHandler) {
        this.signUpHandler = signUpHandler;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpHandler.handle(signUpRequest);
    }

}
