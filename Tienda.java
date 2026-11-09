public class Tienda {
    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    // Procesa el pago del cliente que sigue según la cola de prioridad e imprime la factura
    public void atenderSiguienteCliente() {
        if (colaClientes.estaVacia()) {
            System.out.println("\n--- No hay clientes en la cola para atender ---");
            return;
        }

        Cliente cliente = colaClientes.desencolar();
        System.out.println("\n==============================================");
        System.out.println("             FACTURA DE COMPRA                ");
        System.out.println("==============================================");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Categoría: " + cliente.getTipoPrioridad());
        System.out.println("----------------------------------------------");
        System.out.println("Productos comprados:");
        cliente.getCarrito().imprimirCarrito();
        System.out.println("----------------------------------------------");
        System.out.println("TOTAL A PAGAR: ₡" + cliente.getCarrito().calcularTotal());
        System.out.println("==============================================\n");
    }
}
