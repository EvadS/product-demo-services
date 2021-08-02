package com.se.product.service.domain.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductSearch {
    private String code;
    private String name;
    private String cost;

    private String categoryCode;
}
