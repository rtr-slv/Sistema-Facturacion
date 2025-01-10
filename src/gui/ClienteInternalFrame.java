package gui;

import javax.swing.*;

import controller.ClienteController;
import model.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteInternalFrame extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClienteController clienteController;
    private JTextField txtId, txtNombre, txtDireccion, txtTelefono;
    private JButton btnGuardar, btnCancelar;

    public ClienteInternalFrame(ClienteController clienteController) {
        // Inicializamos el controlador de clientes
        this.clienteController = clienteController;

        // Configuración del InternalFrame
        setTitle("Gestión de Clientes");
        setSize(400, 300);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setLayout(new FlowLayout());

        // Crear los campos de texto
        txtId = new JTextField(20);
        txtNombre = new JTextField(20);
        txtDireccion = new JTextField(20);
        txtTelefono = new JTextField(20);

        // Crear los botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        // Agregar los botones de acción
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarAccion();
            }
        });

        // Agregar componentes al InternalFrame
        add(new JLabel("ID:"));
        add(txtId);
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(btnGuardar);
        add(btnCancelar);
    }

    // Método para guardar un nuevo cliente
    private void guardarCliente() {
        try {
            // Obtener los datos de los campos de texto
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();

            // Crear un nuevo cliente
            Cliente cliente = new Cliente(id, nombre, direccion, telefono);

            // Agregar el cliente al controlador
            clienteController.agregarCliente(cliente);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Cliente guardado con éxito.");

            // Limpiar los campos
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número válido.");
        }
    }

    // Método para cancelar la acción y limpiar los campos
    private void cancelarAccion() {
        limpiarCampos();
        dispose();
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
}
