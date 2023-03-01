package com.acme.reactive.handson.config;

import com.acme.reactive.handson.dto.Response;
import com.acme.reactive.handson.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RequestHandler {
    private final ReactiveMathService mathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        int i = Integer.parseInt(serverRequest.pathVariable("i"));
        return ServerResponse.ok().body(mathService.square(i), Response.class);
    }
}
