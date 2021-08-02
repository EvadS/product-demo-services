package com.se.product.service.repository;


import com.se.product.service.domain.Price;
import com.se.product.service.model.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    boolean existsByCostAndCurrencyType(Double cost, CurrencyType currencyType);
}
