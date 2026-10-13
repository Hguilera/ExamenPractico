import java.io.PrintStream;
import java.util.Scanner;

//Lee líneas completas para evitar problemas//
public final class EntradaConsola {
    private final Scanner entrada;
    private final PrintStream salida;

    public EntradaConsola(Scanner entrada, PrintStream salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    public int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            salida.print(mensaje);
            String texto = entrada.nextLine().trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) return valor;
            } catch (NumberFormatException ex) {
                // Reintentar con un mensaje//
            }
            salida.println("Entrada inválida. Escribe un entero entre " + minimo + " y " + maximo + ".");
        }
    }

    public long leerId() {
        while (true) {
            salida.print("ID de la canción: ");
            try {
                long id = Long.parseLong(entrada.nextLine().trim());
                if (id > 0) return id;
            } catch (NumberFormatException ex) {
                // Reintentar.
            }
            salida.println("Entrada inválida. El ID debe ser un entero positivo.");
        }
    }

    public String leerTexto(String mensaje) {
        while (true) {
            salida.print(mensaje);
            String texto = entrada.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            salida.println("Entrada inválida. El texto no puede estar vacío.");
        }
    }
}
