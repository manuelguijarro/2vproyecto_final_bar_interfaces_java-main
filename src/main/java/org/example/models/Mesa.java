package org.example.models;

public class Mesa {
    private int id;
    private String nombreMesa;
    private Double totalGastado;

    public Mesa(int id, String nombreMesa, Double totalGastado) {
        this.id = id;
        this.nombreMesa = nombreMesa;
        this.totalGastado = totalGastado;
    }

    public Mesa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", nombreMesa='" + nombreMesa + '\'' +
                ", totalGastado=" + totalGastado +
                '}';
    }
}
