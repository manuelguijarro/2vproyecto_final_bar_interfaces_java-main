package org.example.models;

public class Producto {
    private int id;

    private String nombreProducto;
    private String categoriaProducto;
    private Double precioProducto;

    public Producto() {
    }

    public Producto(int id, String nombreProducto, String categoriaProducto, Double precioProducto) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.categoriaProducto = categoriaProducto;
        this.precioProducto = precioProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    @Override
    public String toString() {
        return  nombreProducto + " | " + categoriaProducto + " | " + precioProducto +"€" ;
    }
}
