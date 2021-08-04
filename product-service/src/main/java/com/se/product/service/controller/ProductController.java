package com.se.product.service.controller;

import com.se.product.service.controller.base.ProductApi;
import com.se.product.service.domain.Product;
import com.se.product.service.mapper.ProductMapper;
import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import com.se.product.service.model.ProductItemResponse;
import com.se.product.service.model.payload.PagedProductSearchRequest;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import static com.se.product.service.config.ApplicationConstant.API_VERSION;

@RestController
@RequestMapping("/api/product" + API_VERSION)
public class ProductController implements ProductApi {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Add a product")
    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest product) {
        logger.debug("Create new product: {}", product);
        ProductResponse response = productService.create(product);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid ProductRequest product) {
        logger.debug("update product. id:{}, model:{}", id, product);
        ProductResponse response = productService.update(id, product);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable(value = "id") @NotNull Long id) {
        logger.debug("Handle get product details request, id: {}", id);
        ProductResponse productResponse = productService.get(id);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") @NotNull Long id) {
        logger.debug("Handle delete product request, id: {}", id);
        productService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<ProductResponse> updateCategory(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid CategoriesRequest categoriesRequest) {

        logger.debug("Handle change product categories request, id: {}, categories: {}", id, categoriesRequest);

        ProductResponse productResponse = productService.updateCategories(id, categoriesRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<ProductResponse> updatePrices(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid PricesRequest pricesRequest) {
        logger.debug("Handle change product prices request, id: {}, pricesRequest: {}", id, pricesRequest);

        ProductResponse productResponse = productService.updatePrices(id, pricesRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/paged")
    public ResponseEntity<?> paged(@Valid @RequestBody PagedProductSearchRequest searchRequest){
        logger.debug("Handle get paged products list");

        Page<ProductItemResponse> paged = productService.getPaged(searchRequest);
        return ResponseEntity.ok(paged);
    }

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/list")
    public  ResponseEntity<?> getAll(){

        List<Product> all = productRepository.findAll();

        List<ProductResponse> collect = all.stream()
                .map(ProductMapper.MAPPER::toProductResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
}
