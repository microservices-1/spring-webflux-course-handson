package com.acme.reactive.handson.controller;

import com.acme.reactive.handson.dto.Response;
import com.acme.reactive.handson.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/table/{i}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getTable(@PathVariable int i) {
        return mathService.multiTable(i);
    }
}
