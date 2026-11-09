// Nodo para el árbol binario de productos
class NodoArbol {
    Producto producto;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(Producto producto) {
        this.producto = producto;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolProductos {
    private NodoArbol raiz;

    public void insertar(Producto producto) {
        raiz = insertarRec(raiz, producto);
    }

    // Inserción recursiva usando el nombre del producto como llave (ABB)
    private NodoArbol insertarRec(NodoArbol nodo, Producto producto) {
        if (nodo == null) {
            return new NodoArbol(producto);
        }

        // Uso compareToIgnoreCase para ordenar alfabéticamente sin importar mayúsculas
        int comparacion = producto.getNombre().compareToIgnoreCase(nodo.producto.getNombre());

        if (comparacion < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, producto);
        } else if (comparacion > 0) {
            nodo.derecho = insertarRec(nodo.derecho, producto);
        } else {
            // Si entra aquí es porque ya existe un producto con el mismo nombre
            System.out.println("Ojo: El producto '" + producto.getNombre() + "' ya existe en el inventario.");
        }
        return nodo;
    }

    // Búsqueda en el árbol para cuando el cliente mete cosas al carrito
    public Producto buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Producto buscarRec(NodoArbol nodo, String nombre) {
        if (nodo == null) {
            return null; // No lo encontró
        }
        if (nombre.equalsIgnoreCase(nodo.producto.getNombre())) {
            return nodo.producto;
        }
        if (nombre.compareToIgnoreCase(nodo.producto.getNombre()) < 0) {
            return buscarRec(nodo.izquierdo, nombre);
        }
        return buscarRec(nodo.derecho, nombre);
    }

    // Recorrido InOrden para mostrar los productos ordenados de la A a la Z
    public void mostrarInOrden() {
        if (raiz == null) {
            System.out.println("(Inventario vacío)");
        } else {
            mostrarInOrdenRec(raiz);
        }
    }

    private void mostrarInOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            mostrarInOrdenRec(nodo.izquierdo);
            System.out.println(" - " + nodo.producto);
            mostrarInOrdenRec(nodo.derecho);
        }
    }
}
