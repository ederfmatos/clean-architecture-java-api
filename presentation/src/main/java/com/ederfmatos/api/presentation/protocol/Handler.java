package com.ederfmatos.api.presentation.protocol;

public interface Handler<Request, Response> {

    Response handle(Request request);

}
