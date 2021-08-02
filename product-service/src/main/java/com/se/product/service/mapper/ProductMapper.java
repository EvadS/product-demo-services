package com.se.product.service.mapper;

import com.se.product.service.domain.Product;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 */
@Mapper(uses = { PriceMapper.class })
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "prices", source = "priceSet")
    })
    Product toProduct(ProductRequest productRequest);



    ProductResponse toProductRepository(Product product);

}
