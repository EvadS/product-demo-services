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
    //Category
    private String categoryCode;
    private String categoryName;

    private long dateFrom;
    private long dateTo;
    private String price;
}
