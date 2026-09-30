package com.krdl.task14.config;


public final class AppConstants {

 
  public static final String SWAGGER_UI_PATH = "/swagger-ui";
  public static final String SWAGGER_UI_WILDCARD = "/swagger-ui/**";
  public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
  public static final String API_DOCS_PATH = "/v3/api-docs";
  public static final String API_DOCS_WILDCARD = "/v3/api-docs/**";

  
  public static final String AUTH_METHOD_BASIC = "basic";
  public static final String AUTH_METHOD_API_KEY = "api-key";

 
  public static final String PASSWORD_PREFIX_NOOP = "{noop}";
  public static final String USER_ROLE = "USER";
  public static final String API_KEY_USER_NAME = "api-key-user";


  public static final String ERROR_MISSING_API_KEY = "Missing API Key";
  public static final String ERROR_INVALID_API_KEY = "Invalid API Key";

  
  public static final String DELETE_BOOKS_MESSAGE = "Deleted %d book(s) by author: %s";


  public static final String DEFAULT_PAGE = "0";
  public static final String DEFAULT_SIZE = "10";
  public static final int DEFAULT_PAGE_INT = 0;
  public static final int DEFAULT_SIZE_INT = 10;


  public static final String SWAGGER_SCHEME_NAME = "X-API-KEY";

  private AppConstants() {
    throw new AssertionError("Cannot instantiate utility class");
  }
}
