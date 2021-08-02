package com.se.product.service.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Provide stored price data")
public class PriceResponse {
    @ApiModelProperty(name = "id", notes = "unique identifier", required = true)
    private long id;

    @ApiModelProperty(name = "price", notes = "Price information", required = true)
    private PriceRequest price;

}
