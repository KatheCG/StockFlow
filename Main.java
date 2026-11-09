import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tienda tienda = new Tienda();
        int opcion = 0;

        // Menú principal interactivo
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE INVENTARIO Y VENTAS ---");
            System.out.println("1. Insertar Producto al Inventario");
            System.out.println("2. Mostrar Inventario de Productos");
            System.out.println("3. Registrar Cliente en la Cola (Llenar Carrito)");
            System.out.println("4. Ver Cola de Clientes Pendientes");
            System.out.println("5. Atender Siguiente Cliente (Generar Factura)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Evitar que el programa se caiga si meten letras en vez de números
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombreProd = scanner.nextLine();
                    System.out.print("Precio del producto: ");
                    try {
                        double precioProd = Double.parseDouble(scanner.nextLine());
                        tienda.getInventario().insertar(new Producto(nombreProd, precioProd));
                        System.out.println("¡Producto agregado con éxito!");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El precio debe ser un número.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Inventario Disponible ---");
                    tienda.getInventario().mostrarInOrden();
                    break;

                case 3:
                    System.out.print("Nombre del cliente: ");
                    String nombreCli = scanner.nextLine();
                    System.out.print("Prioridad (1: Básico, 2: Afiliado, 3: Premium): ");
                    int prio = 0;
                    try {
                        prio = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {}

                    if (prio < 1 || prio > 3) {
                        System.out.println("Error: La prioridad debe ser 1, 2 o 3.");
                        break;
                    }

                    Cliente nuevoCliente = new Cliente(nombreCli, prio);
                    String continuar = "s";

                    // Bucle para ir agregando ítems al carrito desde el inventario
                    while (continuar.equalsIgnoreCase("s")) {
                        System.out.println("\nInventario disponible:");
                        tienda.getInventario().mostrarInOrden();
                        System.out.print("Nombre del producto a agregar al carrito: ");
                        String prodBuscado = scanner.nextLine();

                        Producto p = tienda.getInventario().buscar(prodBuscado);
                        if (p != null) {
                            nuevoCliente.getCarrito().agregar(p);
                            System.out.println("¡" + p.getNombre() + " agregado al carrito!");
                        } else {
                            System.out.println("Error: Producto no encontrado en el inventario.");
                        }

                        System.out.print("¿Desea agregar otro producto al carrito? (s/n): ");
                        continuar = scanner.nextLine();
                    }

                    // Encolar al cliente con su carrito listo
                    tienda.getColaClientes().encolar(nuevoCliente);
                    System.out.println("¡Cliente agregado a la cola exitosamente!");
                    break;

                case 4:
                    System.out.println("\n--- Cola de Clientes en Espera ---");
                    tienda.getColaClientes().mostrarCola();
                    break;

                case 5:
                    tienda.atenderSiguienteCliente();
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
