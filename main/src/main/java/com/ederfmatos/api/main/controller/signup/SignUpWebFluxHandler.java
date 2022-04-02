package com.ederfmatos.api.main.controller.signup;

import com.ederfmatos.api.main.config.WebFluxRouteAdapter;
import com.ederfmatos.api.presentation.handler.signup.SignUpHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class SignUpWebFluxHandler {

    @Bean
    public RouterFunction<ServerResponse> routeRequest(SignUpHandler signUpHandler) {
        return RouterFunctions
                .route(POST("/v2/signup").and(accept(MediaType.APPLICATION_JSON)), new WebFluxRouteAdapter<>(signUpHandler, HttpStatus.CREATED));
    }

}
