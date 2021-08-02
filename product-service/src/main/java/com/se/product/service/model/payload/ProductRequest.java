package com.se.product.service.model.payload;

import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Product information")
public class ProductRequest {
    private String name;
    private CategoriesRequest categories;
    private PricesRequest prices;
}
