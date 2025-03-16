package com.example.product_client.controller;

import com.example.product_client.model.Producto;
import com.example.product_client.model.ProductoDTO;
import com.example.product_client.service.ProductoClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ProductoClientController {

    private final ProductoClientService productoClientService;

    public ProductoClientController(ProductoClientService productoClientService) {
        this.productoClientService = productoClientService;
    }



    @GetMapping("/all")
    public List<Producto> getAllProductos() {
        return productoClientService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoClientService.getProductoById(id);
        return producto != null
                ? new ResponseEntity<>(producto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto created = productoClientService.createProducto(producto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto updated = productoClientService.updateProducto(id, producto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Integer id) {
        String result = productoClientService.deleteProducto(id);
        return ResponseEntity.ok(result);
    }
}