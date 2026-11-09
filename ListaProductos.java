// Nodo para la lista enlazada simple del carrito
class NodoLista {
    Producto producto;
    NodoLista siguiente;

    public NodoLista(Producto producto) {
        this.producto = producto;
        this.siguiente = null;
    }
}

public class ListaProductos {
    private NodoLista cabeza;

    // Agrego los productos al final de la lista
    public void agregar(Producto producto) {
        NodoLista nuevo = new NodoLista(producto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLista actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Recorro el carrito acumulando el total a pagar
    public double calcularTotal() {
        double total = 0;
        NodoLista actual = cabeza;
        while (actual != null) {
            total += actual.producto.getPrecio();
            actual = actual.siguiente;
        }
        return total;
    }

    // Imprime la lista de lo que compró el cliente
    public void imprimirCarrito() {
        if (cabeza == null) {
            System.out.println("   (Carrito vacío)");
            return;
        }
        NodoLista actual = cabeza;
        while (actual != null) {
            System.out.println("   - " + actual.producto);
            actual = actual.siguiente;
        }
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}