package com.example.Electro.Verse.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // ✅ Disable CSRF for API
                .authorizeHttpRequests()
                .requestMatchers("/api/registrations/**").permitAll() // public
                .requestMatchers("/api/admin/**").hasRole("ADMIN")   // admin only
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // enable basic auth for admin
        return http.build();
    }
}

