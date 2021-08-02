package com.se.product.service.endpoint;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

//registers the class with Spring WS as a Web Service Endpoint
@Endpoint
public class ProductEndpoint {

    Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);

    private static final String NAMESPACE_URI = "http://www.service.product.se.com/model/soap";

//
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
//    @ResponsePayload
//    public GetProductResponse getProductRequest(@RequestPayload GetProductRequest request) {
//        logger.debug("Handle [get product by code request], request code : {} ", request.getCode());
//        GetProductResponse response = new GetProductResponse();
//
//        response.setProduct(getFakeProduct());
//
//        return response;
//    }
//
//    Product getFakeProduct(){
//        Product product = new Product();
//        product.setCode("COME_FAKE_CODE");
//        product.setId(1000l);
//        product.setName("FAKE PRODUCT NAME");
//
//        return product;
//    }



}
