package controller;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController(List<Cliente> clientes) {
        if (clientes != null) {
            this.clientes = clientes;
        }
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public boolean eliminarCliente(int clienteId) {
        Cliente clienteAEliminar = null;
        for (Cliente cliente : clientes) {
            if (cliente.getId() == clienteId) {
                clienteAEliminar = cliente;
                break;
            }
        }

        if (clienteAEliminar != null) {
            clientes.remove(clienteAEliminar);
            return true;
        }
        return false;
    }
}
