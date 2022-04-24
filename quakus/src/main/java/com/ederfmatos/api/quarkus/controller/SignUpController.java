package com.ederfmatos.api.quarkus.controller;

import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import com.ederfmatos.api.presentation.handler.signup.SignUpRequest;
import com.ederfmatos.api.presentation.handler.signup.SignUpResponse;
import com.ederfmatos.api.quarkus.factory.SignUpFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/quarkus/signup")
@Produces(MediaType.APPLICATION_JSON)
public class SignUpController {

    private final SignUpHandler signUpHandler;

    @Inject
    public SignUpController(SignUpHandler signUpHandler) {
        this.signUpHandler = signUpHandler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return signUpHandler.handle(signUpRequest);
    }

    @GET
    public String hello() {
        return "Fuck";
    }

}
