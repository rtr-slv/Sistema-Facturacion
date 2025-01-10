package controller;

import model.Cliente;
import model.Producto;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<Cliente> clientes;
    private List<Producto> productos;

    public DataManager() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}
