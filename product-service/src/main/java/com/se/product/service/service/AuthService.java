package com.se.product.service.service;

import com.se.product.service.domain.User;
import com.se.product.service.model.CustomUserDetails;
import com.se.product.service.model.payload.LoginRequest;
import com.se.product.service.model.payload.RegistrationRequest;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthService {
    Optional<User> registerUser(RegistrationRequest newRegistrationRequest);

    Boolean emailAlreadyExists(String email);

    Boolean usernameAlreadyExists(String username);

    Optional<Authentication> authenticateUser(LoginRequest loginRequest);

    Boolean currentPasswordMatches(User currentUser, String password);

    String generateToken(CustomUserDetails customUserDetails);

    String generateTokenFromUserId(Long userId);
}
