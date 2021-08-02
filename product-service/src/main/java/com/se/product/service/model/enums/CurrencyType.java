package com.se.product.service.model.enums;

import java.util.stream.Stream;

public enum CurrencyType {
    UAH(1),
    RUB(2),
    USD(3),
    EUR(4);

    private int id;

    CurrencyType(int id) {
        this.id = id;
    }

    public static CurrencyType fromId(int id) {
        return Stream.of(CurrencyType.values())
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    public int getId() {
        return id;
    }
}
