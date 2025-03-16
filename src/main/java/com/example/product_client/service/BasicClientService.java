package com.example.product_client.service;

import com.example.product_client.model.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class BasicClientService {


    private final RestTemplate publicRestTemplate;
    private final String baseUrl;

    public BasicClientService(
            @Qualifier("publicRestTemplate") RestTemplate publicRestTemplate,
            @Value("${producto.service.url}") String baseUrl) {
        this.publicRestTemplate = publicRestTemplate;
        this.baseUrl = baseUrl;
    }

    // Endpoints públicos (sin autenticación)
    public String getPublico() {
        return publicRestTemplate.getForObject(baseUrl + "/api1/publico", String.class);
    }

    public String getPublicoItems() {
        return publicRestTemplate.getForObject(baseUrl + "/api1/publico/items", String.class);
    }

    public String getPublicoNombres() {
        return publicRestTemplate.getForObject(baseUrl + "/api1/publico/nombres", String.class);
    }


    public List<ProductoDTO> getAllProductosDTO() {
        ResponseEntity<List<ProductoDTO>> response = publicRestTemplate.exchange(
                baseUrl + "/allDTO",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductoDTO>>() {}
        );
        return response.getBody();
    }

}