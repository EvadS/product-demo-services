package com.se.product.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ApiModel(description = "Provide model to create new category item")
public class CategoryRequest {

    @ApiModelProperty(name = "name", notes = "category name",
            required = true)
    private String name;

    @ApiModelProperty(name = "code", notes = "category code",
            required = true)
    private String code;

    @ApiModelProperty(name = "baseCategory", notes = "base category id ",
            required = false)
    private Long baseCategory;
}
