package com.se.product.service.model.enums;

import java.util.stream.Stream;

/**
 * The enum Role role.
 */
public enum RoleName {

    /**
     * Role admin authority role.
     */
    ROLE_ADMIN(1),

    /**
     * Role user authority role.
     */
    ROLE_USER(2);

    private int roleId;

    RoleName(int roleId) {
        this.roleId = roleId;
    }

    public static RoleName fromId(int id) {
        return Stream.of(RoleName.values())
                .filter(p -> p.getRoleId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getRoleId() {
        return roleId;
    }
}
