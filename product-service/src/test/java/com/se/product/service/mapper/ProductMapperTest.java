package com.se.product.service.mapper;

import com.se.product.service.domain.Price;
import org.junit.jupiter.api.Test;

class ProductMapperTest {

    @Test
    public void testMapProductModelToProduct(){

        Price price1 = Price.builder()
                .cost(100.0)
                .build();

        Price price2 = Price.builder()
                .cost(200.0)
                .build();

//        Product product = Product.

        //        assertThat( customer.getOrderItems() )
//                .extracting( "name", "quantity" )
//                .containsExactly( tuple( "Table", 2L ) );

    }

//
//    @Test
//    public void testMapDtoToEntity() {
//
//        CustomerDto customerDto = new CustomerDto();
//        customerDto.id = 10L;
//        customerDto.customerName = "Filip";
//        OrderItemDto order1 = new OrderItemDto();
//        order1.name = "Table";
//        order1.quantity = 2L;
//        customerDto.orders = new ArrayList<>( Collections.singleton( order1 ) );
//
//        Customer customer = CustomerMapper.MAPPER.toCustomer( customerDto );
//
//        assertThat( customer.getId() ).isEqualTo( 10 );
//        assertThat( customer.getName() ).isEqualTo( "Filip" );

//    }

}