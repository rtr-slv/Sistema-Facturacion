package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import controller.ProductoController;
import model.Cliente;
import model.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestionarFacturacionFrame extends JFrame {
    private ClienteController clienteController;
    private ProductoController productoController;

    // Componentes de la interfaz
    private JComboBox<String> comboClientes;
    private JComboBox<String> comboProductos;
    private JTextField txtCantidad;
    private JButton btnAgregarDetalle;
    private JButton btnFinalizarFactura;
    private JTable tablaDetalles;
    private DefaultTableModel tableModel;

    // Lista de detalles de la factura
    private List<String[]> detallesFactura = new ArrayList<>();

    public GestionarFacturacionFrame(ClienteController clienteController, ProductoController productoController) {
        this.clienteController = clienteController;
        this.productoController = productoController;

        setTitle("Gestión de Facturación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        // Panel superior para selección de cliente y producto
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(3, 2));

        panelSuperior.add(new JLabel("Cliente:"));
        comboClientes = new JComboBox<>();
        cargarClientes();
        panelSuperior.add(comboClientes);

        panelSuperior.add(new JLabel("Producto:"));
        comboProductos = new JComboBox<>();
        cargarProductos();
        panelSuperior.add(comboProductos);

        panelSuperior.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelSuperior.add(txtCantidad);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla de detalles de la factura
        tableModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Precio Unitario", "Subtotal"}, 0);
        tablaDetalles = new JTable(tableModel);
        add(new JScrollPane(tablaDetalles), BorderLayout.CENTER);

        // Botones para agregar detalle y finalizar factura
        JPanel panelBotones = new JPanel();
        btnAgregarDetalle = new JButton("Agregar Detalle");
        btnFinalizarFactura = new JButton("Finalizar Factura");
        panelBotones.add(btnAgregarDetalle);
        panelBotones.add(btnFinalizarFactura);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón Agregar Detalle
        btnAgregarDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarDetalle();
            }
        });

        // Acción del botón Finalizar Factura
        btnFinalizarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarFactura();
            }
        });
    }

    // Cargar clientes en el combo box
    private void cargarClientes() {
        comboClientes.removeAllItems();
        for (Cliente cliente : clienteController.listarClientes()) {
            comboClientes.addItem(cliente.getId() + " - " + cliente.getNombre());
        }
    }

    // Cargar productos en el combo box
    private void cargarProductos() {
        comboProductos.removeAllItems();
        for (Producto producto : productoController.listarProductos()) {
            comboProductos.addItem(producto.getId() + " - " + producto.getNombre());
        }
    }

    // Método para agregar un detalle a la factura
    private void agregarDetalle() {
        if (comboProductos.getSelectedItem() == null || txtCantidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto y escriba la cantidad.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }

            // Obtener datos del producto seleccionado
            String productoSeleccionado = comboProductos.getSelectedItem().toString();
            int productoId = Integer.parseInt(productoSeleccionado.split(" - ")[0]);
            Producto producto = productoController.buscarProductoPorId(productoId);

            if (producto == null) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                return;
            }

            // Verificar stock disponible
            if (cantidad > producto.getStock()) {
                JOptionPane.showMessageDialog(this, "No hay suficiente stock para este producto.");
                return;
            }

            // Calcular subtotal
            double subtotal = cantidad * producto.getPrecio();

            // Agregar detalle a la lista y la tabla
            detallesFactura.add(new String[]{producto.getNombre(), String.valueOf(cantidad), String.valueOf(producto.getPrecio()), String.valueOf(subtotal)});
            tableModel.addRow(new Object[]{producto.getNombre(), cantidad, producto.getPrecio(), subtotal});

            // Actualizar stock del producto
            producto.setStock(producto.getStock() - cantidad);
            productoController.actualizarProducto(producto);

            txtCantidad.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido mayor a 0.");
        }
    }

    // Método para finalizar la factura
    private void finalizarFactura() {
        if (detallesFactura.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay detalles en la factura.");
            return;
        }

        // Mostrar resumen de la factura
        StringBuilder resumen = new StringBuilder("Factura generada con éxito:\n\n");
        double totalFactura = 0;

        for (String[] detalle : detallesFactura) {
            resumen.append(detalle[0]).append(" - Cantidad: ").append(detalle[1])
                   .append(", Precio Unitario: ").append(detalle[2])
                   .append(", Subtotal: ").append(detalle[3]).append("\n");
            totalFactura += Double.parseDouble(detalle[3]);
        }

        resumen.append("\nTotal: ").append(totalFactura).append(" USD");

        JOptionPane.showMessageDialog(this, resumen.toString());

        // Limpiar tabla y lista de detalles
        tableModel.setRowCount(0);
        detallesFactura.clear();
    }
}
