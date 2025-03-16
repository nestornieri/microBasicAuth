package com.example.product_client.model;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private String codigoBarras;
    private Integer idCategoria;
    private String nombreCategoria;
}
