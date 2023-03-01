package com.acme.reactive.handson.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MultiplyRequest {
    private int first;
    private int second;
}
