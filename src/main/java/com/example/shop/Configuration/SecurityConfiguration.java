package com.example.shop.Configuration;

import com.example.shop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsConfiguration userDetailsConfiguration() {
        return new UserDetailsConfiguration();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req->req
                        .requestMatchers("user/login").permitAll()
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/user/register","/user/save-user").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form ->form.loginPage("/user/login")
                .loginProcessingUrl("/login")
                                .permitAll()
                                .successHandler((request, response, authentication) -> {
                                    if (authentication.getAuthorities().stream()
                                            .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                                        response.sendRedirect("/admin/dashboard");
                                    } else {
                                        response.sendRedirect("/user/home");
                                    }
                                })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/user/login")
                        .permitAll()
                );

        return http.build();
    }
}
