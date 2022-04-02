package com.ederfmatos.api.main.config.handler;

import com.ederfmatos.api.domain.exception.EmailAlreadyRegisteredException;
import com.ederfmatos.api.domain.exception.InvalidParamException;
import com.ederfmatos.api.domain.exception.RequiredFieldException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Configuration
@Order(-2)
public class WebFluxRouteExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    public WebFluxRouteExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
        try {
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            if (throwable instanceof RequiredFieldException requiredFieldException) {
                return handleWithStatus(exchange, ErrorResponse.of(requiredFieldException), HttpStatus.BAD_REQUEST);
            }

            if (throwable instanceof InvalidParamException invalidParamException) {
                return handleWithStatus(exchange, ErrorResponse.of(invalidParamException), HttpStatus.BAD_REQUEST);
            }

            if (throwable instanceof EmailAlreadyRegisteredException emailAlreadyRegisteredException) {
                return handleWithStatus(exchange, ErrorResponse.of(emailAlreadyRegisteredException), HttpStatus.CONFLICT);
            }

            if (throwable instanceof ResponseStatusException responseStatusException) {
                return handleWithStatus(exchange, new ErrorResponse(responseStatusException.getReason()), responseStatusException.getStatus());
            }

            throw throwable;
        } catch (Throwable exception) {
            exception.printStackTrace();
            return handleServerError(exchange);
        }
    }

    private Mono<Void> handleServerError(ServerWebExchange exchange) {
        byte[] bytes = "{\"error\": \"Ocorreu um erro interno\"}".getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(bytes);
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    private Mono<Void> handleWithStatus(ServerWebExchange exchange, ErrorResponse errorResponse, HttpStatus status) throws JsonProcessingException {
        exchange.getResponse().setStatusCode(status);
        return handleBody(exchange, errorResponse);
    }

    private Mono<Void> handleBody(ServerWebExchange exchange, ErrorResponse errorResponse) throws JsonProcessingException {
        byte[] bytes = objectMapper.writeValueAsBytes(errorResponse);
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}
