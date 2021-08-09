package com.se.product.service.service.impl;

import com.se.product.service.repository.RoleRepository;
import com.se.product.service.domain.Role;
import com.se.product.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl  implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find all roles from the database
     */
    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
