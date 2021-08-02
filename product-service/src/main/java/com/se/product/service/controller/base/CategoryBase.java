package com.se.product.service.controller.base;

import com.se.product.service.exception.model.ErrorResponse;
import com.se.product.service.model.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "Category Api",
        description = "REST APIs related to category entity")
public interface CategoryBase {

    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Successfully created item",
                    response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource",
                    response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",
                    response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "Incorrect request param", response = ErrorResponse.class),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class),
            @ApiResponse(code = 415, message = "Incorrect model type to create ", response = ErrorResponse.class)

    })
    @ApiOperation(value = "Create category.", nickname = "update-category",
            notes = "Create category.", tags = {}, response = CategoryResponse.class)
    ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request);

    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully updated item"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters"),
            @ApiResponse(code = 400, message = "Incorrect request parameters"),
    })
    @ApiOperation(value = "Update category.", nickname = "update-category",
            notes = "Update category.", tags = {})
    ResponseEntity<CategoryResponse> update(
            @ApiParam(value = "Category id", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long priceId,
            @ApiParam(value = "Category details for update", required = true)
            @Valid @RequestBody CategoryRequest requestModel);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated item"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters"),
            @ApiResponse(code = 400, message = "Incorrect request parameters"),
    })
    @ApiOperation(value = "Change base category id", nickname = "change-base",
            notes = "Change base category id.", tags = {})
    ResponseEntity<CategoryResponse> changeBase(
            @ApiParam(value = "ID of price", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long priceId,
            @ApiParam(value = "base category id",  required = true, example = "123")
            @PathVariable(value = "base-id") @NotNull Long baseId);

    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Item successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Delete category.", nickname = "delete",
            notes = "Delete price by id.",
            tags = {})
    ResponseEntity deleteItem(
            @ApiParam(value = "ID of price to return", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id);


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully, describe category"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Categories", nickname = "price-get",
            notes = "Get category by id.", tags = {})
    ResponseEntity<CategoryResponse> getById(
            @ApiParam(value = "Category unique identifier", required = true, example = "123")
            @PathVariable(value = "id") Long id);


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully list of all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
     })
    @GetMapping(value = "/list")
    @ApiOperation(value = "list", nickname = "list",
            notes = "Current categories.", tags = {})
    // TODO skiea: create object tot return list
    ResponseEntity<CategoryResponseList> list();
}
