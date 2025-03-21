package com.example.product_client.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean(name = "publicRestTemplate")
    public RestTemplate publicRestTemplate() {
        return new RestTemplate(); // Sin autenticación
    }
}