package com.acme.reactive.handson.config;

import com.acme.reactive.handson.dto.MultiplyRequest;
import com.acme.reactive.handson.dto.Response;
import com.acme.reactive.handson.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
        if (i <= 0) {
            return Mono.error(new IllegalArgumentException("must be > 0"));
        }
        return ServerResponse.ok().body(mathService.square(i), Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        int i = Integer.parseInt(serverRequest.pathVariable("i"));
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(mathService.multiTable(i), Response.class);
    }

    public Mono<ServerResponse> multiHandler(ServerRequest serverRequest) {
        Mono<MultiplyRequest> requestMono = serverRequest.bodyToMono(MultiplyRequest.class);
        Mono<Response> responseMono = mathService.multiply(requestMono);
        return ServerResponse.ok()
                .body(responseMono, Response.class);
    }

}
