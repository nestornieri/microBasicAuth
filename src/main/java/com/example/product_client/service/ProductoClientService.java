package com.example.product_client.service;
import com.example.product_client.model.Producto;
import com.example.product_client.model.ProductoDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductoClientService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    public ProductoClientService(
            @Qualifier("authRestTemplate") RestTemplate restTemplate,
            DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    private String getServiceUrl() {
        return discoveryClient.getInstances("producto-service")
                .stream()
                .findFirst()
                .map(serviceInstance -> serviceInstance.getUri().toString())
                .orElseThrow(() -> new RuntimeException("Producto Service no disponible"));
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
        String baseUrl = getServiceUrl();
        ResponseEntity<List<Producto>> response = restTemplate.exchange(
                baseUrl + "/listarProductos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Producto>>() {}
        );
        return response.getBody();
    }

    public Producto getProductoById(Integer id) {
        String baseUrl = getServiceUrl();
        return restTemplate.getForObject(baseUrl + "/{id}", Producto.class, id);
    }

    public Producto createProducto(Producto producto) {
        String baseUrl = getServiceUrl();
        return restTemplate.postForObject(baseUrl + "/create", producto, Producto.class);
    }

    public Producto updateProducto(Integer id, Producto producto) {
        String baseUrl = getServiceUrl();
        restTemplate.put(baseUrl + "/{id}", producto, id);
        return producto;
    }

    public String deleteProducto(Integer id) {
        String baseUrl = getServiceUrl();
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