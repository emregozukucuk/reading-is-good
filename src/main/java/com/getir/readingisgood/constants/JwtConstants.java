package com.getir.readingisgood.constants;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class JwtConstants {
    public static final String SIGNING_KEY = "getir_key";
    public static final long EXPIRATION_TIME = 60 * 60 * 1000; // 60 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/api/v1/login";
    public static final String REGISTER_URL = "/api/v1/customer/register";
    public static final String API_DOCS_URL = "/v2/api-docs";
    public static final String CONFIG_UI_URL = "/configuration/ui";
    public static final String SWAGGER_RESOURCES_URL = "/swagger-resources/**";
    public static final String CONGIS_SECURITY_URL = "/configuration/security";
    public static final String SWAGGER_UI_URL = "/swagger-ui.html";
    public static final String WEBJARS_URL = "/webjars/**";
    public static final int AUTHORIZATION_BEARER_LENGTH = 7;
    public static final String EMAIL_KEY = "email";

}
