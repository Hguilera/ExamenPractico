import java.util.NoSuchElementException;
import java.util.Scanner;

// Aplicación de consola//
public final class Main {
    public static void main(String[] args) {
        ListaCircularDoble lista = new ListaCircularDoble();
        try (Scanner scanner = new Scanner(System.in)) {
            EntradaConsola entrada = new EntradaConsola(scanner, System.out);
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                int opcion = entrada.leerEntero("Elige una opción: ", 1, 13);
                switch (opcion) {
                    case 1:
                    case 2:
                        String titulo = entrada.leerTexto("Título: ");
                        String artista = entrada.leerTexto("Artista: ");
                        int segundos = entrada.leerEntero("Duración en segundos: ", 1, Integer.MAX_VALUE);
                        Cancion nueva = opcion == 1
                                ? lista.agregarInicio(titulo, artista, segundos)
                                : lista.agregarFinal(titulo, artista, segundos);
                        System.out.println("Agregada: " + nueva);
                        break;
                    case 3:
                        if (vacia(lista)) break;
                        Cancion encontrada = lista.buscar(entrada.leerId());
                        System.out.println(encontrada == null ? "No existe una canción con ese ID." : encontrada);
                        break;
                    case 4:
                        if (vacia(lista)) break;
                        if (lista.seleccionar(entrada.leerId())) mostrarActual(lista.consultarActual());
                        else System.out.println("No existe una canción con ese ID.");
                        break;
                    case 5:
                        if (vacia(lista)) break;
                        Cancion eliminada = lista.eliminarPorId(entrada.leerId());
                        System.out.println(eliminada == null ? "No existe una canción con ese ID." : "Eliminada: " + eliminada);
                        break;
                    case 6:
                        Cancion anterior = lista.eliminarActual();
                        if (anterior == null) System.out.println("La lista está vacía.");
                        else {
                            System.out.println("Eliminada: " + anterior);
                            mostrarActual(lista.consultarActual());
                        }
                        break;
                    case 7: mostrarActual(lista.consultarActual()); break;
                    case 8: mostrarActual(lista.avanzar()); break;
                    case 9: mostrarActual(lista.retroceder()); break;
                    case 10:
                        System.out.println("--- Del inicio al final ---");
                        lista.mostrarAdelante(System.out);
                        System.out.println("--- Del final al inicio ---");
                        lista.mostrarAtras(System.out);
                        break;
                    case 11: System.out.println("Cantidad de canciones: " + lista.getCantidad()); break;
                    case 12:
                        if (vacia(lista)) break;
                        int k = entrada.leerEntero("¿Cuántas canciones deseas reproducir?: ", 1, Integer.MAX_VALUE);
                        lista.reproducir(k, System.out);
                        System.out.print("Próxima canción. ");
                        mostrarActual(lista.consultarActual());
                        break;
                    case 13:
                        continuar = false;
                        System.out.println("Programa finalizado.");
                        break;
                    default: throw new IllegalStateException("Opción no validada.");
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("\nEntrada finalizada. Programa cerrado.");
        }
    }

    private static boolean vacia(ListaCircularDoble lista) {
        if (lista.getCantidad() != 0) return false;
        System.out.println("La lista está vacía.");
        return true;
    }

    private static void mostrarActual(Cancion cancion) {
        System.out.println(cancion == null ? "La lista está vacía." : "Canción actual: " + cancion);
    }

    private static void mostrarMenu() {
        System.out.println("\n GESTOR DE LISTAS DE REPRODUCCIÓN");
        System.out.println("1. Agregar al inicio\n2. Agregar al final\n3. Buscar por ID");
        System.out.println("4. Seleccionar por ID\n5. Eliminar por ID\n6. Eliminar canción actual");
        System.out.println("7. Consultar actual\n8. Siguiente canción\n9. Canción anterior");
        System.out.println("10. Mostrar lista en ambos sentidos\n11. Consultar cantidad");
        System.out.println("12. Simular reproducción\n13. Salir");
    }
}
