package com.se.product.service.domain.converters;

import com.se.product.service.model.enums.RoleName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleName, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RoleName roleName) {
        if (roleName == null) {
            return null;
        }
        return roleName.getRoleId();
    }

    @Override
    public RoleName convertToEntityAttribute(Integer roleId) {
        if (roleId == null) {
            return null;
        }

        return RoleName.fromId(roleId);
    }
}