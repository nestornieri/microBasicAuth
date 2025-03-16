package com.example.product_client.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${producto.service.url}")
    private String baseUrl;

    @Value("${producto.service.username}")
    private String username;

    @Value("${producto.service.password}")
    private String password;

    @Bean(name = "authRestTemplate")
    public RestTemplate authRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor(username, password)
        );
        return restTemplate;
    }

    @Bean(name = "publicRestTemplate")
    public RestTemplate publicRestTemplate() {
        return new RestTemplate(); // Sin autenticación
    }
}