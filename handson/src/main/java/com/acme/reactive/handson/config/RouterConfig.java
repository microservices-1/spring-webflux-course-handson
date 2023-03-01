package com.acme.reactive.handson.config;

import com.acme.reactive.handson.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {
    private final RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route()
                .GET("/r-fun/square/{i}", requestHandler::squareHandler)
                .GET("/r-fun/table/{i}", requestHandler::tableHandler)
                .POST("/r-fun/multi", requestHandler::multiHandler)
                .onError(IllegalArgumentException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return (err, rq) -> {
            IllegalArgumentException exception = (IllegalArgumentException) err;
            return ServerResponse.badRequest().bodyValue(new ErrorResponse(exception.getMessage()));
        };
    }
}
