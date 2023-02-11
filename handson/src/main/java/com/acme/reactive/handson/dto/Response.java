package com.acme.reactive.handson.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Response {
    private final LocalDateTime time = LocalDateTime.now();
    private final int result;

    public Response(int result) {
        this.result = result;
    }
}
