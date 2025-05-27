package com.fotova.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageDto {
    private List<ProductDtoBack> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalData;
}