package controller;

import model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    private List<Producto> productos = new ArrayList<>();

    // Agregar producto
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Listar productos
    public List<Producto> listarProductos() {
        return productos;
    }

    // Buscar producto por ID
    public Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null; // Si no se encuentra el producto
    }

    // Actualizar producto
    public void actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado); // Reemplazar el producto existente
                return;
            }
        }
    }


    // Eliminar producto
    public boolean eliminarProducto(int id) {
        Producto productoAEliminar = buscarProductoPorId(id);
        if (productoAEliminar != null) {
            productos.remove(productoAEliminar);
            return true;
        }
        return false; // Si no se encuentra el producto
    }
}

