package com.example.product_client.controller;

import com.example.product_client.model.ProductoDTO;
import com.example.product_client.service.BasicClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/api1")
public class BasicClientController {

    private final BasicClientService basicClientService;

    public BasicClientController(BasicClientService basicClientService) {
        this.basicClientService = basicClientService;
    }

    @GetMapping("/publico")
    public String getPublico() {
        return basicClientService.getPublico();
    }

    @GetMapping("/publico/items")
    public String getPublicoItems() {
        return basicClientService.getPublicoItems();
    }

    @GetMapping("/publico/nombres")
    public String getPublicoNombres() {
        return basicClientService.getPublicoNombres();
    }

    @GetMapping("/allDTO")
    public List<ProductoDTO> getAllProductosDTO() {
        return basicClientService.getAllProductosDTO();
    }

}