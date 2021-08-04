package com.se.product.service.mapper;

import com.se.product.service.controller.ProductController;
import com.se.product.service.domain.Category;
import com.se.product.service.domain.Price;
import com.se.product.service.domain.Product;
import com.se.product.service.domain.specification.ProductSearch;
import com.se.product.service.model.IdName;
import com.se.product.service.model.ProductItemResponse;
import com.se.product.service.model.payload.PagedProductSearchRequest;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Mapper(uses = {PriceMapper.class})
public interface ProductMapper {

    Logger logger = LoggerFactory.getLogger(ProductMapper.class);
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "prices", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Product toProduct(ProductRequest productRequest);


    // TODO: implement full product response
    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "id", source = "id"),
            @Mapping(source = "categories", target = "categories", qualifiedByName = "categoriesToStringSet"),
            @Mapping(source = "prices", target = "prices", qualifiedByName = "pricesToStringSet")
    })
    ProductResponse toProductResponse(Product product);

    @Named("categoriesToStringSet")
    static Set<IdName> categoriesToNames(Set<Category> categorySet) {

        if(categorySet==null)
            return  new HashSet<>();

        Set<IdName> categories = categorySet.stream().
                map(it -> {
                    return  new IdName(it.getId(), it.getName());
                })
                .collect(Collectors.toSet());

        String categoriesStr = categories.stream()
                .map(IdName::getName)
                .collect(Collectors.joining(","));

        logger.info("Mapped categories to product response: {}", categoriesStr);

        return  categories;
    }


    @Named("pricesToStringSet")
    static Set<IdName> pricesToStringSet(Set<Price> priceSet) {

        if(priceSet==null)
            return  new HashSet<>();

        Set<IdName> prices = priceSet.stream().
                map(it -> {
                    return  new IdName(it.getId(), String.valueOf(it.getCost()));
                })
                .collect(Collectors.toSet());

        String pricesStr = prices.stream()
                .map(IdName::getName)
                .collect(Collectors.joining(","));

        logger.debug("Mapped prices to product response: {}", pricesStr);

        return  prices;
    }

    @Mappings({
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "cost", source = "cost"),

            @Mapping(target = "categoryCode", source = "categoryCode"),
            @Mapping(target = "categoryName", source = "categoryName"),

            @Mapping(target = "dateFrom", source = "dateFrom"),
            @Mapping(target = "dateTo", source = "dateTo")
    })
    ProductSearch toProductSearch(PagedProductSearchRequest searchRequest);


    @Mappings({
            @Mapping(target = "name", source = "name"),
      })
    ProductItemResponse toProductItemResponse(Product product);
}
