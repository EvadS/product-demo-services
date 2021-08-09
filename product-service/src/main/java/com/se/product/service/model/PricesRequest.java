package com.se.product.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PricesRequest",
        description = "Provide base information about product prices")
public class PricesRequest {
    @ApiModelProperty(value = "Set of prices ids")
    private Set<Long> prices = new HashSet<>();
}
