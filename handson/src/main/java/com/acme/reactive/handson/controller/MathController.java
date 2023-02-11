package com.acme.reactive.handson.controller;

import com.acme.reactive.handson.dto.Response;
import com.acme.reactive.handson.service.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/math")
public class MathController {
    private final MathService mathService;

    @GetMapping("/square/{i}")
    public Response getSquare(@PathVariable int i) {
        return mathService.square(i);
    }

    @GetMapping("/table/{i}")
    public List<Response> getTable(@PathVariable int i) {
        return mathService.multiTable(i);
    }
}
