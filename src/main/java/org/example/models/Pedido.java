package org.example.models;

public class Pedido {
    private int idMesaActual;
    private int idProducto;


    public Pedido() {
    }

    public Pedido(int idMesaActual, int idProducto) {
        this.idMesaActual = idMesaActual;
        this.idProducto = idProducto;
    }

    public int getIdMesaActual() {
        return idMesaActual;
    }

    public void setIdMesaActual(int idMesaActual) {
        this.idMesaActual = idMesaActual;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idMesaActual=" + idMesaActual +
                ", IdProducto='" + idProducto + '\'' +
                '}';
    }
}
