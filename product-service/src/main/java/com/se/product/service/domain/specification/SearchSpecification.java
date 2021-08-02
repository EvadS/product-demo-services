package com.se.product.service.domain.specification;

import org.springframework.data.jpa.domain.Specification;

public abstract class SearchSpecification<T, U> {
    public abstract Specification<T> getFilter(U request);
}