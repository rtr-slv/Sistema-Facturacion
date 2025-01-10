package gui;

import model.Cliente;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes; // Lista de clientes a mostrar en la tabla
    private String[] columnNames = {"ID", "Nombre", "Dirección", "Teléfono"}; // Nombres de las columnas

    // Constructor que inicializa la lista de clientes
    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Retorna el número de filas en la tabla (número de clientes)
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    // Retorna el número de columnas (en este caso, 4 columnas)
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Retorna el nombre de la columna (utilizado para el encabezado de la tabla)
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // Retorna el valor de la celda en una fila y columna dadas
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getId(); // ID del cliente
            case 1:
                return cliente.getNombre(); // Nombre del cliente
            case 2:
                return cliente.getDireccion(); // Dirección del cliente
            case 3:
                return cliente.getTelefono(); // Teléfono del cliente
            default:
                return null;
        }
    }

    // Método para actualizar la lista de clientes y refrescar la tabla
    public void actualizarClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged(); // Notifica que los datos han cambiado
    }
}
