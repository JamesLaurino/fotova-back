package com.fotova.dto.product;

public class ProductDtoBack {

    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;
    private CategoryInnerProductDto categoryInnerProductDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CategoryInnerProductDto getCategoryInnerProductDto() {
        return categoryInnerProductDto;
    }

    public void setCategoryInnerProductDto(CategoryInnerProductDto categoryInnerProductDto) {
        this.categoryInnerProductDto = categoryInnerProductDto;
    }
}