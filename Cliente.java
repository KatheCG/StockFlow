public class Cliente {
    private String nombre;
    private int prioridad; // 1: Básico, 2: Afiliado, 3: Premium
    private ListaProductos carrito; // Cada cliente tiene su propio carrito de compras

    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    // Para mostrar el texto de la categoría según el entero de prioridad
    public String getTipoPrioridad() {
        switch (prioridad) {
            case 3: return "Premium";
            case 2: return "Afiliado";
            case 1: return "Básico";
            default: return "Desconocido";
        }
    }
}
