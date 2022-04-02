package com.ederfmatos.api.main.config;

import com.ederfmatos.api.presentation.protocol.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;

public class WebFluxRouteAdapter<Request> implements HandlerFunction<ServerResponse> {

    private static final Logger LOG = LoggerFactory.getLogger(WebFluxRouteAdapter.class);

    private final Class<Request> requestClass;
    private final Handler<Request, ?> handler;
    private final HttpStatus responseStatus;

    public WebFluxRouteAdapter(Handler<Request, ?> handler) {
        this(handler, HttpStatus.OK);
    }

    public WebFluxRouteAdapter(Handler<Request, ?> handler, HttpStatus responseStatus) {
        this((Class<Request>) ((ParameterizedType) handler.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0], handler, responseStatus);
    }

    public WebFluxRouteAdapter(Class<Request> requestClass, Handler<Request, ?> handler, HttpStatus responseStatus) {
        this.requestClass = requestClass;
        this.handler = handler;
        this.responseStatus = responseStatus;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        LOG.info("Start request to [{}] with method [{}]", request.uri(), request.method());

        return request.bodyToMono(requestClass)
                .flatMap(handlerRequest -> {
                    Object response = handler.handle(handlerRequest);
                    return ServerResponse.status(responseStatus).bodyValue(response);
                })
                .doOnError(error -> LOG.error("An error occurred on request to [{}] with method [{}] - [{}]", request.uri().getPath(), request.method(), error.toString()))
                .doOnSuccess(response -> LOG.info("Success on request to [{}] with method [{}]", request.uri().getPath(), request.method()))
                .doOnTerminate(() -> LOG.info("Finish request to [{}] with method [{}]", request.uri(), request.method()));
    }

}
