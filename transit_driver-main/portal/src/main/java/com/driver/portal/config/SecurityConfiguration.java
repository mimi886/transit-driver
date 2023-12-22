package com.driver.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
            .csrf((crsf)->crsf.disable())
            .authorizeHttpRequests((authorizeRequests)->
                                            authorizeRequests
                                            .requestMatchers("/api/**","/dashboard/**","/**").permitAll())
            ;

        return http.build();
    }
    
}
