package com.example.product_client.model;

import lombok.Data;

@Data
public class Producto {
    private Integer idProducto;
    private String nombre;
    private String codigoBarras;
    private String precioVenta; // Mantengo String para coincidir con el original
    private Integer cantidadStock;
    private Boolean estado;
    private Categoria categoria; // Relación con Categoria
}
