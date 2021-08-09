package com.se.product.service.model.search;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class PriceSearch {

    private  String currency;
    private String product;

    private String priceFrom;
    private  String priceTo;
}
