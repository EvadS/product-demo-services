package com.se.product.service.service.impl;


import com.se.product.service.model.payload.RegistrationRequest;
import com.se.product.service.domain.Role;
import com.se.product.service.domain.User;
import com.se.product.service.repository.UserRepository;
import com.se.product.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleServiceImpl roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    /**
     * Finds a user in the database by username
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Finds a user in the database by email
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find a user in db by id.
     */
    @Override
    public Optional<User> findById(Long Id) {
        return userRepository.findById(Id);
    }

    /**
     * Save the user to the database
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Check is the user exists given the email: naturalId
     */
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Check is the user exists given the username: naturalId
     */
    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    /**
     * Creates a new user from the registration request
     */
    @Override
    public User createUser(RegistrationRequest registerRequest) {
        User newUser = new User();
        Boolean isNewUserAsAdmin = registerRequest.getRegisterAsAdmin();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setUsername(registerRequest.getEmail());
        newUser.addRoles(getRolesForNewUser(isNewUserAsAdmin));
        newUser.setActive(true);
        newUser.setEmailVerified(false);
        return newUser;
    }

    /**
     * Performs a quick check to see what roles the new user could be assigned to.
     *
     * @return list of roles for the new user
     */
    @Override
    public Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin) {
        Set<Role> newUserRoles = new HashSet<>(roleService.findAll());
        if (!isToBeMadeAdmin) {
            newUserRoles.removeIf(Role::isAdminRole);
        }
        logger.info("Setting user roles: " + newUserRoles);
        return newUserRoles;
    }
}
