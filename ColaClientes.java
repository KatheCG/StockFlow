// Nodo para la cola de prioridad
class NodoCola {
    Cliente cliente;
    NodoCola siguiente;

    public NodoCola(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }
}

public class ColaClientes {
    private NodoCola frente;

    // Inserción con prioridad: los de mayor prioridad van adelante.
    // Si hay empate en prioridad, quedan atrás por orden de llegada.
    public void encolar(Cliente cliente) {
        NodoCola nuevo = new NodoCola(cliente);

        // Si está vacía o el cliente que viene tiene MÁS prioridad que el primero
        if (frente == null || cliente.getPrioridad() > frente.cliente.getPrioridad()) {
            nuevo.siguiente = frente;
            frente = nuevo;
        } else {
            // Busco la posición adecuada manteniendo la prioridad y el FIFO en empates
            NodoCola actual = frente;
            while (actual.siguiente != null && actual.siguiente.cliente.getPrioridad() >= cliente.getPrioridad()) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    // Atender al cliente con mayor prioridad (el primero de la cola)
    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }
        Cliente clienteAtendido = frente.cliente;
        frente = frente.siguiente;
        return clienteAtendido;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    // Muestra la cola actual para ver cómo quedaron ordenados
    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("(La cola está vacía)");
            return;
        }
        NodoCola actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.cliente.getNombre() +
                    " [Prioridad: " + actual.cliente.getTipoPrioridad() + " (" + actual.cliente.getPrioridad() + ")]");
            actual = actual.siguiente;
            posicion++;
        }
    }
}
