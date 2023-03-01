package com.acme.reactive.handson.service;

import com.acme.reactive.handson.dto.MultiplyRequest;
import com.acme.reactive.handson.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
public class ReactiveMathService {

    public Mono<Response> square(int x) {
        return Mono.fromSupplier(() -> new Response(x));
    }

    public Flux<Response> multiTable(int x) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500))
                .map(i -> new Response(i * x))
                .log();

    }

    public Mono<Response> multiply(Mono<MultiplyRequest> requestMono) {
        return requestMono.map(rq -> rq.getFirst() * rq.getSecond())
                .map(Response::new);
    }
}
