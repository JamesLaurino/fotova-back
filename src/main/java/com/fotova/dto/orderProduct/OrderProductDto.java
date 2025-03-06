package com.fotova.dto.orderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class OrderProductDto {
    private Integer orderId;
    private String creationDate;
    private String name;
    private String email;
    private Integer quantity;
    private Double price;
    private Double total;
}