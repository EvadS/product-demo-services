package com.se.product.service.service.impl;

import com.se.product.service.domain.Category;
import com.se.product.service.domain.Price;
import com.se.product.service.domain.Product;
import com.se.product.service.domain.specification.ProductSearch;
import com.se.product.service.domain.specification.ProductSpecification;
import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.exception.model.ResourceNotFoundException;
import com.se.product.service.mapper.ProductMapper;
import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import com.se.product.service.model.ProductItemResponse;
import com.se.product.service.model.payload.PagedProductSearchRequest;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.repository.PriceRepository;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;
    private final ProductSpecification productSpecification;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository, ProductSpecification productSpecification) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productSpecification = productSpecification;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Validate.notBlank(productRequest.getName());

        logger.debug("Create new product, name: {}", productRequest.getName());
        boolean exists = productRepository.existsByName(productRequest.getName());

        if (exists) {
            throw new AlreadyExistException("Product", "name", productRequest.getName());
        }

        Product product = ProductMapper.MAPPER.toProduct(productRequest);
        updateProductPrices(productRequest.getPrices(), product);
        updateProductCategories(productRequest.getCategories(), product);

        productRepository.save(product);

        logger.info("Product, name:'{}' created", product.getName());

        return ProductMapper.MAPPER.toProductResponse(product);
    }


    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {

        // check product exists
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id));

        // check is name available
        if (!StringUtils.isBlank(productRequest.getName())) {
            boolean alreadyExists = productRepository.existsByNameAndIdNot(productRequest.getName(), id);
            if (alreadyExists) {
                throw new AlreadyExistException("Product", "name", productRequest.getName());
            } else {
                product.setName(productRequest.getName());
            }
        }

        updateProductPrices(productRequest.getPrices(), product);
        updateProductCategories(productRequest.getCategories(), product);

        logger.info("Product with name:{} updated", product.getName());

        return ProductMapper.MAPPER.toProductResponse(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id));

        logger.debug("Delete product: {}", id);
        Optional.ofNullable(product.getCategories()).ifPresent(categories ->
                categories.forEach(it -> {
                    product.removeCategory(it);
                    logger.debug("Removed category: {} ", it.getId());
                }));

        Optional.ofNullable(product.getPrices()).ifPresent(prices -> prices.forEach(it -> {
            product.removePrice(it);
            logger.debug("Removed price: {} ", it.getId());
        }));

        logger.info("Product, id:{} removed", id);
    }

    @Override
    public ProductResponse updateCategories(Long id, CategoriesRequest categoriesRequest) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id));

        updateProductCategories(categoriesRequest, product);

        logger.debug("Updated categories on product: {}", id);
        return ProductMapper.MAPPER.toProductResponse(product);
    }

    @Override
    public ProductResponse updatePrices(Long id, PricesRequest pricesRequest) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id));

        updateProductPrices(pricesRequest, product);

        logger.debug("Updated prices on product: {}", id);
        return ProductMapper.MAPPER.toProductResponse(product);
    }

    @Override
    public Page<ProductItemResponse> getPaged(PagedProductSearchRequest searchRequest) {

        Pageable pageable = PageRequest.of(
                searchRequest.getPage(),
                searchRequest.getCount(),
                Sort.by("createdAt").descending());

        ProductSearch productSearch = ProductMapper.MAPPER.toProductSearch(searchRequest);

        return productRepository.findAll(productSpecification
                .getFilter(productSearch), pageable)
                .map(ProductMapper.MAPPER::toProductItemResponse);
    }

    @Override
    public ProductResponse get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", id));

        return ProductMapper.MAPPER.toProductResponse(product);
    }


    private void updateProductCategories(CategoriesRequest categoriesRequest, Product product) {
        Optional.ofNullable(product.getCategories()).ifPresent(
                i -> product.getCategories()
                        .forEach(product::removeCategory));

        logger.debug("Removed current categories to product");

        if (categoriesRequest == null)
            return;

        categoriesRequest.getCategories()
                .forEach(item -> {
                    Category category = categoryRepository.findById(item).orElseThrow(
                            () -> new ResourceNotFoundException("Category", "id", item));

                    logger.debug("added category id:{} to product name:{}", item, product.getName());
                    product.addCategory(category);
                });
    }

    private void updateProductPrices(PricesRequest pricesRequest, Product product) {

        Optional.ofNullable(product.getPrices()).ifPresent(
                i -> product.getPrices()
                        .forEach(product::removePrice));

        logger.debug("Removed all current prices of product");

        if (pricesRequest == null)
            return;

        pricesRequest.getPrices()
                .forEach(item -> {
                    Price price = priceRepository.findById(item).orElseThrow(
                            () -> new ResourceNotFoundException("Price", "id", item));

                    logger.debug("added price id:{} to product", item);
                    product.addPrice(price);
                });
    }

}
