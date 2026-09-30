package com.krdl.task13.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

  private final String header;
  private final String expectedValue;

  public ApiKeyAuthFilter(String header, String expectedValue) {
    this.header = header;
    this.expectedValue = expectedValue;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.startsWith(AppConstants.SWAGGER_UI_PATH)
        || path.startsWith(AppConstants.API_DOCS_PATH);
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    String apiKey = request.getHeader(header);

    if (apiKey == null || apiKey.isBlank()) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(AppConstants.ERROR_MISSING_API_KEY);
      return;
    }

    if (!expectedValue.equals(apiKey)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(AppConstants.ERROR_INVALID_API_KEY);
      return;
    }

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            AppConstants.API_KEY_USER_NAME,
            null,
            Collections.emptyList()
        );

    SecurityContextHolder.getContext().setAuthentication(auth);

    filterChain.doFilter(request, response);
  }
}