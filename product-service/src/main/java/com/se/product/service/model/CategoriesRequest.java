package com.se.product.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ProductRequest",
        description = "Provide base information about product categories")
public class CategoriesRequest {
    @ApiModelProperty(value = "Set of categories ids")
    private Set<Long> categories;
}
