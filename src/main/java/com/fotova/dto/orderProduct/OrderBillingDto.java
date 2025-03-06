package com.fotova.dto.orderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderBillingDto {
    private String name;
    private Integer quantity;
    private Double price;
}
