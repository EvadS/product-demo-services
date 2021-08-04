package com.se.product.service.domain.specification;

import org.springframework.data.jpa.domain.Specification;

public abstract class SearchSpecification<T, U> {

    private final String wildcard = "%";

    public abstract Specification<T> getFilter(U request);

    protected String containsLowerCase(String searchField) {
        return wildcard + searchField.toLowerCase() + wildcard;
    }

}