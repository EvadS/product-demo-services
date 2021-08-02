package com.se.product.service.service;

import com.se.product.service.model.PriceRequest;
import com.se.product.service.model.PriceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PriceService {
    PriceResponse create(PriceRequest request);

    PriceResponse updatePrice(Long priceId, PriceRequest requestModel);

    void deletePrice(Long id);

    PriceResponse getById(Long priceId);

    List<PriceResponse> getAll();

    Page<PriceResponse> getPaged(Pageable pageable, String filter);
}
