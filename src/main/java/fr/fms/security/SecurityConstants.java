package fr.fms.security;



public class SecurityConstants {
    public static final String HEADER_STRING = "Authorization";
    public static final String SECRET = "chat";
    public static final long EXPIRATION_TIME = 100 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ERROR_MESSAGE = "Username or password incorrect";
}
