package com.makichanov.projectmanagement.security;

/**
 * The interface Token service.
 */
public interface TokenService {

    /**
     * Generate token string.
     *
     * @param username the username
     * @param password the password
     * @return the string
     */
    String generateToken(String username, String password);

    /**
     * Validate token boolean.
     *
     * @param token the token
     * @return the boolean
     */
    boolean validateToken(String token);

    /**
     * Gets login from token.
     *
     * @param token the token
     * @return the login from token
     */
    String getLoginFromToken(String token);

}
