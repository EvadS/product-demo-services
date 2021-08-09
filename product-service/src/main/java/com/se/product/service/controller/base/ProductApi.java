package com.se.product.service.controller.base;


import com.se.product.service.exception.model.ErrorResponse;
import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.CategoryResponse;
import com.se.product.service.model.PricesRequest;
import com.se.product.service.model.payload.PagedProductSearchRequest;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.net.HttpURLConnection.*;

@Api(value = "product Api", description = "Operations pertaining to products in Online Store")
public interface ProductApi {

    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK,
                    message = "Successfully created item", response = CategoryResponse.class),
            @ApiResponse(code = HTTP_BAD_REQUEST, message = "Incorrect request parameters"),
            @ApiResponse(code = HTTP_UNAUTHORIZED, message = "You are not authorized to view the resource",
                    response = ErrorResponse.class),
            @ApiResponse(code = HTTP_FORBIDDEN, message = "Accessing the resource you were trying to reach is forbidden",
                    response = ErrorResponse.class),
            @ApiResponse(code = HTTP_CONFLICT, message = "Incorrect request param", response = ErrorResponse.class),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class),
            @ApiResponse(code = HTTP_UNSUPPORTED_TYPE, message = "Incorrect model type to create", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Create product", notes = "This method creates a new product")
    ResponseEntity<ProductResponse> saveProduct(
            @ApiParam(value = "The values to new product entity", required = true, name = "product")
            @RequestBody @Valid ProductRequest product);

    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Successfully created item", response = ProductResponse.class),
            @ApiResponse(code = 400, message = "Incorrect request parameters", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource",
                    response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",
                    response = ErrorResponse.class),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "The resource you were trying to update is not found",
                    response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "Incorrect request param", response = ErrorResponse.class),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class),
    })
    @ApiOperation(value = "Update product", notes = "This method update existed product")
    ResponseEntity<ProductResponse> update(
            @ApiParam(value = "Product unique identifier", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id,

            @ApiParam(value = "The values to new product entity", required = true, name = "product")
            @RequestBody @Valid ProductRequest product);


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully, describe product", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Get product",
            notes = "Get product full information.")
    ResponseEntity<ProductResponse> getById(
            @ApiParam(value = "Product unique identifier", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id);


    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Item successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Delete", notes = "Delete product by unique identifier.")
    ResponseEntity delete(
            @ApiParam(value = "Product unique identifier", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id);


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Item successfully updated", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters"),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Update categories", notes = "Update products categories")
    ResponseEntity<ProductResponse> updateCategory(
            @ApiParam( value = "Product unique identifier", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id,

            @ApiParam(value = "New categories id", name = "categories", required = true)
            @RequestBody @Valid CategoriesRequest categoriesRequest);


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Item successfully updated", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters"),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Update prices", notes = "Update products prices")
    ResponseEntity<ProductResponse> updatePrices(
            @PathVariable(value = "id") @NotNull Long id,
            @ApiParam(value = "New prices ids", name = "categories", required = true)
            @RequestBody @Valid PricesRequest pricesRequest);


    ResponseEntity<?> paged(@Valid @RequestBody PagedProductSearchRequest searchRequest);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully list of all items"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })

    @ApiOperation(value = "list", nickname = "list",
            notes = "All available products.")
    ResponseEntity<?> getAll();
}
