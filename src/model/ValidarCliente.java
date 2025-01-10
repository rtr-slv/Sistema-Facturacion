package model;

public class ValidarCliente {
    public boolean esClienteValido(Cliente cliente) {
        return cliente != null && cliente.getId() > 0;
    }
}
