package com.ederfmatos.api.main.controller.signup;

import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.handler.signup.SignUpRequest;
import com.ederfmatos.api.presentation.handler.signup.SignUpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/signup")
public class SignUpController {

    private final SignUpHandler signUpHandler;

    public SignUpController(SignUpHandler signUpHandler) {
        this.signUpHandler = signUpHandler;
    }

    @PostMapping
    public SignUpResponse signup(@RequestBody SignUpRequest signUpRequest) {
        return this.signUpHandler.handle(signUpRequest);
    }

}
