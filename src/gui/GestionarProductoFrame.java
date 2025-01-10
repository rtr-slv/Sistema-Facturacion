package gui;

import javax.swing.*;
import controller.ProductoController;
import model.Producto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class GestionarProductoFrame extends JFrame {
    private ProductoController productoController;
    private JTable tableProductos;
    private DefaultTableModel tableModel;

    public GestionarProductoFrame(ProductoController productoController) {
        this.productoController = productoController;
        setTitle("Gestionar Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Botones y tabla
        JButton btnAgregar = new JButton("Agregar Producto");
        JButton btnEliminar = new JButton("Eliminar Producto");
        panel.add(btnAgregar, BorderLayout.NORTH);
        panel.add(btnEliminar, BorderLayout.SOUTH);

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio", "Stock"}, 0);
        tableProductos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableProductos);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        // Cargar los productos en la tabla
        cargarProductos();

        // Acción del botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        // Acción del botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }

    // Método para cargar productos en la tabla
    private void cargarProductos() {
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar

        // Cargar los productos desde el controlador
        for (Producto producto : productoController.listarProductos()) {
            tableModel.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock()});
        }
    }

    // Método para agregar un producto
    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del Producto:");
        String precioText = JOptionPane.showInputDialog(this, "Precio del Producto:");
        String stockText = JOptionPane.showInputDialog(this, "Stock del Producto:");

        if (nombre == null || precioText == null || stockText == null) {
            return;
        }

        double precio;
        int stock;

        try {
            precio = Double.parseDouble(precioText);
            stock = Integer.parseInt(stockText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un precio y stock válidos.");
            return;
        }

        Producto producto = new Producto(0, nombre, precio, stock);
        productoController.agregarProducto(producto);
        cargarProductos();
    }

    // Eliminar un producto seleccionado de la tabla
    private void eliminarProducto() {
        int selectedRow = tableProductos.getSelectedRow();

        if (selectedRow != -1) {
            // Obtener el ID del producto seleccionado
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            
            // Eliminar producto usando el controlador
            productoController.eliminarProducto(id);

            // Recargar la tabla
            cargarProductos();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto para eliminar.");
        }
    }
}
