package com.se.product.service.service;

import com.se.product.service.domain.Role;
import com.se.product.service.domain.User;
import com.se.product.service.model.payload.RegistrationRequest;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long Id);

    User save(User user);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User createUser(RegistrationRequest registerRequest);

    Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin);
}
