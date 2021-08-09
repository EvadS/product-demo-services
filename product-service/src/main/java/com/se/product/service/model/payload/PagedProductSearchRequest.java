package com.se.product.service.model.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
public class PagedProductSearchRequest {
    @Min(0)
    private int page;
    @Min(1)
    private int count;

    private String price;
    private String name;
    private String cost;

    private String categoryCode;
    private String categoryName;

    private long dateFrom;
    private long dateTo;
}
