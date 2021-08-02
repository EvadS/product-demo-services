package com.se.product.service.mapper;


import com.se.product.service.domain.Price;
import com.se.product.service.model.PriceRequest;
import com.se.product.service.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

    PriceMapper MAPPER = Mappers.getMapper(PriceMapper.class);

    @Mappings({
            @Mapping(target = "currencyType", source = "currencyType"),
            @Mapping(target = "cost", source = "cost")
    })
    Price toPrice(PriceRequest priceRequest);



    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "price.cost", source = "cost"),
            @Mapping(target = "price.currencyType", source = "currencyType")
    })
    PriceResponse toPriceResponse(Price price);

}
