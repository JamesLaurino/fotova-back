package com.fotova.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderBasketDto {
    private String id;
    private Integer productId;
    private Integer quantity;
    private String email;
}
