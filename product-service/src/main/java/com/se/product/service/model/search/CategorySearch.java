package com.se.product.service.model.search;

import lombok.Data;
import lombok.NoArgsConstructor;

// search by category
@Data
@NoArgsConstructor
public class CategorySearch {
    private String categorySearch;
    private String categoryName;

}
