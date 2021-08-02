package com.se.product.service.service;

import com.se.product.service.model.CategoryRequest;
import com.se.product.service.model.CategoryResponse;
import com.se.product.service.model.CategoryResponseList;

public interface CategoryService {
    /**
     * create new category
     * @param request category model to create
     * @return
     */
    CategoryResponse create(CategoryRequest request);

    CategoryResponse updateItem(Long id, CategoryRequest requestModel);

    CategoryResponse changeBase(Long id, Long baseId);

    void deletePrice(Long id);

    CategoryResponse getById(Long id);

    CategoryResponseList getAll();
}
