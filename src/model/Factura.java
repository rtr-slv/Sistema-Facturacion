package model;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int id;
    private Cliente cliente;
    private String fecha;
    private double total;
    private List<DetalleFactura> detalles;

    // Constructor
    public Factura(Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = 0.0;
        this.detalles = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    // Método para agregar un detalle a la factura
    public void agregarDetalle(Producto producto, int cantidad) {
        DetalleFactura detalle = new DetalleFactura(producto, cantidad);
        detalles.add(detalle);
        // Actualizar el total de la factura
        this.total += detalle.getSubtotal();
    }
}

