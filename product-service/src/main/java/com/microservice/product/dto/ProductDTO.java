package com.microservice.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private String productDescription;
    private double productPrice;
}
