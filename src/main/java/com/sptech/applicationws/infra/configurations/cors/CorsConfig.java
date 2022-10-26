package com.sptech.applicationws.infra.configurations.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String[] PERMITED_URLS = {
            "http://localhost:3000",
            "https://t-veste.vercel.app/",
            "https://t-veste.vercel.app",
            "http://44.196.96.99:3000",
            "http://3.212.236.122:3000",
	    "https://tveste.ddns.net"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(PERMITED_URLS)
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
