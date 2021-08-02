package com.se.product.service.mapper;

import com.se.product.service.domain.Price;
import com.se.product.service.model.PriceRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceMapperTest {

    @Test
    public void testPriceRequestToPrice() {

        PriceRequest priceRequest = PriceRequest.builder()
                .cost(Double.valueOf(1))
                .build();

        Price price = PriceMapper.MAPPER.toPrice(priceRequest);
        assertThat(price.getCost()).isEqualTo(priceRequest.getCost());

    }


}