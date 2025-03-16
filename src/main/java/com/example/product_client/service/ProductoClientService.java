package com.example.product_client.service;
import com.example.product_client.model.Producto;
import com.example.product_client.model.ProductoDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductoClientService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ProductoClientService(
            @Qualifier("authRestTemplate") RestTemplate restTemplate, // Especificar authRestTemplate
            @Value("${producto.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }
    /*
    public List<ProductoDTO> getAllProductosDTO() {
        ResponseEntity<List<ProductoDTO>> response = restTemplate.exchange(
                baseUrl + "/allDTO",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductoDTO>>() {}
        );
        return response.getBody();
    }
    */
    public List<Producto> getAllProductos() {
        ResponseEntity<List<Producto>> response = restTemplate.exchange(
                baseUrl + "/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Producto>>() {}
        );
        return response.getBody();
    }

    public Producto getProductoById(Integer id) {
        return restTemplate.getForObject(baseUrl + "/{id}", Producto.class, id);
    }

    public Producto createProducto(Producto producto) {
        return restTemplate.postForObject(baseUrl + "/create", producto, Producto.class);
    }

    public Producto updateProducto(Integer id, Producto producto) {
        restTemplate.put(baseUrl + "/{id}", producto, id);
        return producto;
    }

    public String deleteProducto(Integer id) {
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                id
        );
        return response.getBody();
    }
}