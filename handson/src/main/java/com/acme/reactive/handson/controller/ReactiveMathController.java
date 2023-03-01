package com.acme.reactive.handson.controller;

import com.acme.reactive.handson.dto.ErrorResponse;
import com.acme.reactive.handson.dto.MultiplyRequest;
import com.acme.reactive.handson.dto.Response;
import com.acme.reactive.handson.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/r-math")
public class ReactiveMathController {
    private final ReactiveMathService mathService;

    @GetMapping("/square/{i}")
    public Mono<Response> getSquare(@PathVariable int i) {
        return mathService.square(i);
    }


    @GetMapping("/square/{i}/error")
    public Mono<Response> getSquareError(@PathVariable int i) {

        return Mono.just(i)
                .handle((integer, synchronousSink) -> {
                    if (integer <= 0) {
                        synchronousSink.error(new IllegalArgumentException("Should be > 0"));
                    } else {
                        synchronousSink.next(integer);
                    }
                }).cast(Integer.class)
                .flatMap(x -> mathService.square(x));
    }


    @GetMapping(value = "/table/{i}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getTable(@PathVariable int i) {
        return mathService.multiTable(i);
    }

    @PostMapping(value = "/multi")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> requestMono) {
        return mathService.multiply(requestMono);
    }
}
