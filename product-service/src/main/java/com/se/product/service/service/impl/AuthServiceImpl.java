package com.se.product.service.service.impl;


import com.se.product.service.domain.User;
import com.se.product.service.exception.ResourceAlreadyInUseException;
import com.se.product.service.model.CustomUserDetails;
import com.se.product.service.model.payload.LoginRequest;
import com.se.product.service.model.payload.RegistrationRequest;
import com.se.product.service.security.JwtTokenProvider;
import com.se.product.service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserServiceImpl userService;
    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserServiceImpl userService, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user in the database by performing a series of quick checks.
     *
     * @return A user object if successfully created
     */
    @Override
    public Optional<User> registerUser(RegistrationRequest newRegistrationRequest) {
        String newRegistrationRequestEmail = newRegistrationRequest.getEmail();
        if (emailAlreadyExists(newRegistrationRequestEmail)) {
            logger.error("Email already exists: " + newRegistrationRequestEmail);
            throw new ResourceAlreadyInUseException("Email", "Address", newRegistrationRequestEmail);
        }
        logger.info("Trying to register new user [" + newRegistrationRequestEmail + "]");
        User newUser = userService.createUser(newRegistrationRequest);
        User registeredNewUser = userService.save(newUser);
        return Optional.ofNullable(registeredNewUser);
    }

    /**
     * Checks if the given email already exists in the database repository or not
     *
     * @return true if the email exists else false
     */
    @Override
    public Boolean emailAlreadyExists(String email) {
        return userService.existsByEmail(email);
    }

    /**
     * Checks if the given email already exists in the database repository or not
     *
     * @return true if the email exists else false
     */
    @Override
    public Boolean usernameAlreadyExists(String username) {
        return userService.existsByUsername(username);
    }

    /**
     * Authenticate user and log them in given a loginRequest
     */
    @Override
    public Optional<Authentication> authenticateUser(LoginRequest loginRequest) {
        return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword())));
    }


    /**
     * Validates the password of the current logged in user with the given password
     */
    @Override
    public Boolean currentPasswordMatches(User currentUser, String password) {
        return passwordEncoder.matches(password, currentUser.getPassword());
    }

    /**
     * Generates a JWT token for the validated client
     */
    @Override
    public String generateToken(CustomUserDetails customUserDetails) {
        return tokenProvider.generateToken(customUserDetails);
    }

    /**
     * Generates a JWT token for the validated client by userId
     */
    @Override
    public String generateTokenFromUserId(Long userId) {
        return tokenProvider.generateTokenFromUserId(userId);
    }

}
