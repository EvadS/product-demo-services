package com.se.product.service.model.payload;

import com.se.product.service.model.IdName;
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
@ApiModel(value = "ProductResponse",
        description = "Provide information about one stored product")
public class ProductResponse {
    @ApiModelProperty(value = "unique identifier of product")
    private Long id;

    @ApiModelProperty(value = "name of product")
    private String name;

    @ApiModelProperty(value = "Set of product categories")
    private Set<IdName> categories = new HashSet<>();

    @ApiModelProperty(value = "Set of product prices")
    private Set<IdName> prices = new HashSet<>();
}
