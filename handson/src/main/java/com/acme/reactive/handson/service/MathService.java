package com.acme.reactive.handson.service;

import com.acme.reactive.handson.Util;
import com.acme.reactive.handson.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class MathService {

    public Response square(int x) {
        return new Response(x * x);
    }

    public List<Response> multiTable(int x) {
        return IntStream.rangeClosed(1, 10)
                .peek(i -> log.info("Process: {}", i))
                .peek(i -> Util.sleepMillis(500))
                .mapToObj(i -> new Response(x * i))
                .collect(Collectors.toList());
    }
}
