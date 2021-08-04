package com.se.product.service.model.payload;

import com.se.product.service.model.IdName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Set<IdName> categories = new HashSet<>();
    private Set<IdName> prices = new HashSet<>();

}
