package com.se.product.service.model.payload;

import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ProductRequest",
        description = "Provide account model")
public class ProductRequest {
    @NotBlank(message = "Product name cannot be empty")
    @ApiModelProperty(value = "name for the product")
    private String name;

    @ApiModelProperty(value = "Set of categories for a product")
    private CategoriesRequest categories;

    @ApiModelProperty(value = "Set of prices for a product")
    private PricesRequest prices;
}
