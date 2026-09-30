package com.krdl.task13.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.auth")
public class AuthProperties {

  private String method;
  private Basic basic = new Basic();
  private ApiKey apiKey = new ApiKey();

  public static class Basic {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
  }

  public static class ApiKey {
    private String header;
    private String value;

    public String getHeader() { return header; }
    public void setHeader(String header) { this.header = header; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
  }

  public String getMethod() { return method; }
  public void setMethod(String method) { this.method = method; }

  public Basic getBasic() { return basic; }
  public ApiKey getApiKey() { return apiKey; }
}