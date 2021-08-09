package com.se.product.service.controller;


import com.se.product.service.controller.base.CategoryApi;
import com.se.product.service.model.CategoryRequest;
import com.se.product.service.model.CategoryResponse;
import com.se.product.service.model.CategoryResponseList;
import com.se.product.service.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryApi {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid CategoryRequest request) {
        logger.info("handle create category request {}", request);

        CategoryResponse categoryResponse = categoryService.create(request);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable(value = "id") @NotNull Long id,
                                                   @Valid @RequestBody CategoryRequest requestModel) {

        logger.debug("handle update category, id:{}, model: {}", id, requestModel);

        CategoryResponse categoryResponse = categoryService.updateItem(id, requestModel);
        return new ResponseEntity<>(categoryResponse, HttpStatus.ACCEPTED);
    }

    @Override
    @PutMapping("/{id}/base-id")
    public ResponseEntity<CategoryResponse> changeBase(
            @PathVariable(value = "id") @NotNull Long id,
            @PathVariable(value = "base-id") @NotNull Long baseId) {

        logger.debug("handle change base category id:{}, new base category: {}", id, baseId);

        CategoryResponse categoryResponse = categoryService.changeBase(id, baseId);
        return new ResponseEntity<>(categoryResponse, HttpStatus.ACCEPTED);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteItem(@PathVariable(value = "id") @NotNull Long id) {
        logger.debug("handle delete category request, id:{}", id);
        categoryService.deletePrice(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable(value = "id") Long id) {
        logger.debug("handle get category by id: {}", id);

        CategoryResponse categoryResponse = categoryService.getById(id);
        return ResponseEntity.ok(categoryResponse);
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<CategoryResponseList> list() {
        logger.info("get list");

        CategoryResponseList categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
}
