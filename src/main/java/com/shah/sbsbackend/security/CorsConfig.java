package com.shah.sbsbackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.SecretKey;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all origins
                .allowedOrigins("https://melodic-jelly-a38d33.netlify.app/")  // Replace with your Angular app URL
                .allowedMethods("GET", "POST", "PUT")  // Allowed HTTP methods
                .allowedHeaders("*")
                .allowCredentials(true)  // Allow cookies and credentials
                .maxAge(3600);  // Max validity of CORS pre-flight request
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecretKey secretKey(){
        MacAlgorithm alg = Jwts.SIG.HS256;
        return alg.key().build();
    }
}