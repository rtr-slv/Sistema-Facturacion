package gui;

import javax.swing.*;
import controller.ClienteController;
import model.Cliente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class GestionarClientesFrame extends JFrame {
    private ClienteController clienteController;
    private JTable tableClientes;
    private DefaultTableModel tableModel;

    public GestionarClientesFrame(ClienteController clienteController) {
        this.clienteController = clienteController;
        setTitle("Gestionar Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Botones y tabla
        JButton btnAgregar = new JButton("Agregar Cliente");
        JButton btnEliminar = new JButton("Eliminar Cliente");
        panel.add(btnAgregar, BorderLayout.NORTH);
        panel.add(btnEliminar, BorderLayout.SOUTH);

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Dirección", "Teléfono"}, 0);
        tableClientes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableClientes);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        // Cargar los clientes en la tabla
        cargarClientes();

        // Acción del botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        // Acción del botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });
    }

    // Método para cargar clientes en la tabla
    private void cargarClientes() {
        tableModel.setRowCount(0); // Limpiar tabla antes de cargar

        // Cargar los clientes desde el controlador
        for (Cliente cliente : clienteController.listarClientes()) {
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono()});
        }
    }

    // Método para agregar un cliente
    private void agregarCliente() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del Cliente:");
        String direccion = JOptionPane.showInputDialog(this, "Dirección del Cliente:");
        String telefono = JOptionPane.showInputDialog(this, "Teléfono del Cliente:");

        if (nombre == null || direccion == null || telefono == null) {
            return;
        }

        Cliente cliente = new Cliente(0, nombre, direccion, telefono); // ID generado manualmente si es necesario
        clienteController.agregarCliente(cliente);
        cargarClientes();
    }

    // Método para eliminar un cliente
    private void eliminarCliente() {
        int selectedRow = tableClientes.getSelectedRow();

        if (selectedRow != -1) {
            // Obtener el ID del cliente seleccionado
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            
            // Eliminar cliente usando el controlador
            clienteController.eliminarCliente(id);

            // Recargar la tabla
            cargarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente para eliminar.");
        }
    }
}

