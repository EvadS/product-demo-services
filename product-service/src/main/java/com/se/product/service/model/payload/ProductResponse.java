package com.se.product.service.model.payload;

import com.se.product.service.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;

    private Product body;
}
