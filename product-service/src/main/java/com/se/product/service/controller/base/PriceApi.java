package com.se.product.service.controller.base;

import com.se.product.service.exception.model.ErrorResponse;
import com.se.product.service.model.PriceRequest;
import com.se.product.service.model.PriceResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "Price API",
        description = "REST APIs related to price Entity")
public interface PriceApi {

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created price",
                    response = PriceResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource",
                    response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden",
                    response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "Incorrect request param", response = ErrorResponse.class),
            @ApiResponse(code = 422, message = "Incorrect model to create", response = ErrorResponse.class),
            @ApiResponse(code = 415, message = "Incorrect model type to create ", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Create price.", nickname = "update-price",
            notes = "Create price.", tags = {})
    ResponseEntity<PriceResponse> create(@Valid @RequestBody PriceRequest request);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated price"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Update price.", nickname = "update-price",
            notes = "Update price.", tags = {})
    ResponseEntity<?> update(
            @ApiParam(value = "ID of price", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long priceId,
            @ApiParam(value = "Price  details for update", required = true)
            @Valid @RequestBody PriceRequest requestModel);

    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully deleted price"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Delete price.", nickname = "delete",
            notes = "Delete price by id.",
            tags = {})
    ResponseEntity<?> deletePrice(
            @ApiParam(value = "ID of price to return", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully, describe price info "),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 400, message = "Incorrect request parameters")
    })
    @ApiOperation(value = "Price.", nickname = "price-get",
            notes = "Get price by id.", tags = {})
    ResponseEntity<PriceResponse> getById(
            @ApiParam(value = "ID of price to return", required = true, example = "123")
            @PathVariable(value = "id") Long priceId);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully list of all prices"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/list")
    @ApiOperation(value = "Current prices", nickname = "list",
            notes = "Prices list.", tags = {})
    ResponseEntity<?> list();


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully paged list of all prices"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiOperation(value = "Current prices", nickname = "list",
            notes = "Prices list with pagination.", tags = {})
    ResponseEntity<Page<PriceResponse>> getPaged(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer count,
            @RequestParam(required = false, defaultValue = "") String filter);
}
