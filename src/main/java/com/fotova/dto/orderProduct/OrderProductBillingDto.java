package com.fotova.dto.orderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class OrderProductBillingDto {

    private Integer orderId;
    private Double total;
    private String creationDate;
    private List<OrderBillingDto> productBillingDtoList;
}