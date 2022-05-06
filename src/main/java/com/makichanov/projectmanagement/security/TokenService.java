package com.makichanov.projectmanagement.security;

public interface TokenService {

    String generateToken(String username, String password);

    boolean validateToken(String token);

    String getLoginFromToken(String token);

}
