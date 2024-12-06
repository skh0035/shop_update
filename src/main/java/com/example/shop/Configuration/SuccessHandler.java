package com.example.shop.Configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {




  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
        UserDetailsConfiguration userDetails = (UserDetailsConfiguration)authentication.getPrincipal();

        String username = userDetails.getUsername();
        Long userId = userDetails.getUserId();

        String redirect = UriComponentsBuilder.fromPath("/user/"+userId+"/products").build().toUriString();

        response.sendRedirect(redirect);
  }
}
