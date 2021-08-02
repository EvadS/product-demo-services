package com.se.product.service.domain.converters;

import com.se.product.service.model.enums.CurrencyType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class CurrencyConverter implements AttributeConverter<CurrencyType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CurrencyType currencyType) {
        if (currencyType == null) {
            return null;
        }
        return currencyType.getId();
    }

    @Override
    public CurrencyType convertToEntityAttribute(Integer currencyTypeId) {
        if (currencyTypeId == null) {
            return null;
        }

        return CurrencyType.fromId(currencyTypeId);
    }
}