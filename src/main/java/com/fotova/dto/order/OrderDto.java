package com.fotova.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Instant createAt;
    private OrderClientDto client;
}