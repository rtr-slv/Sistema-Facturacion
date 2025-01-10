package gui;

import javax.swing.*;
import java.awt.*;
import controller.ClienteController;
import controller.ProductoController;

public class MainFrame extends JFrame {
    private ClienteController clienteController;
    private ProductoController productoController;

    public MainFrame() {
        clienteController = new ClienteController(null);
        productoController = new ProductoController();

        setTitle("Sistema de Facturación");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuClientes = new JMenu("Clientes");
        JMenuItem itemGestionarClientes = new JMenuItem("Gestionar Clientes");
        itemGestionarClientes.addActionListener(e -> openGestionarClientesFrame());
        menuClientes.add(itemGestionarClientes);
        menuBar.add(menuClientes);

        JMenu menuFacturacion = new JMenu("Facturación");
        JMenuItem itemGestionarFacturacion = new JMenuItem("Gestionar Facturación");
        itemGestionarFacturacion.addActionListener(e -> openGestionarFacturacionFrame());
        menuFacturacion.add(itemGestionarFacturacion);
        menuBar.add(menuFacturacion);

        JMenu menuProductos = new JMenu("Productos");
        JMenuItem itemGestionarProductos = new JMenuItem("Gestionar Productos");
        itemGestionarProductos.addActionListener(e -> openGestionarProductoFrame());
        menuProductos.add(itemGestionarProductos);
        menuBar.add(menuProductos);

        setJMenuBar(menuBar);
    }

    private void openGestionarClientesFrame() {
        new GestionarClientesFrame(clienteController).setVisible(true);
    }

    private void openGestionarFacturacionFrame() {
        new GestionarFacturacionFrame(clienteController, productoController).setVisible(true);
    }

    private void openGestionarProductoFrame() {
        new GestionarProductoFrame(productoController).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

