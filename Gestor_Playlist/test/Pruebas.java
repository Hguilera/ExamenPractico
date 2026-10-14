import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

// Pruebas para la lista de reproducción
public class Pruebas {

    private static int aprobadas = 0;

    public static void main(String[] args) {
        probarListaVacia();
        probarAgregarCanciones();
        probarRecorridos();
        probarSeleccionarYBuscar();
        probarEliminarCanciones();
        probarReproduccion();
        probarDatosIncorrectos();

        System.out.println("\nPruebas aprobadas: " + aprobadas);
    }

    // Revisar que la lista inicie vacía
    private static void probarListaVacia() {
        ListaCircularDoble lista = new ListaCircularDoble();

        comprobar(lista.getCantidad() == 0, "La lista inicia vacía");
        comprobar(lista.consultarActual() == null, "No hay canción actual");
        comprobar(lista.avanzar() == null, "Avanzar con lista vacía");
        comprobar(lista.retroceder() == null, "Retroceder con lista vacía");
        comprobar(lista.eliminarActual() == null, "Eliminar actual con lista vacía");

        lista.verificarConsistencia();
    }

    // Revisar las inserciones
    private static void probarAgregarCanciones() {
        ListaCircularDoble lista = new ListaCircularDoble();

        Cancion primera = lista.agregarFinal("Canción A", "Artista A", 180);
        Cancion segunda = lista.agregarFinal("Canción B", "Artista B", 200);
        Cancion tercera = lista.agregarInicio("Canción C", "Artista C", 150);

        comprobar(lista.getCantidad() == 3, "Agregar canciones");
        comprobar(lista.consultarActual().getId() == primera.getId(),
                "La primera canción agregada sigue siendo la actual");
        comprobar(lista.buscar(segunda.getId()) != null, "Buscar una canción existente");
        comprobar(lista.buscar(999999) == null, "Buscar una canción inexistente");

        lista.verificarConsistencia();
    }

    // Revisar los recorridos en ambos sentidos
    private static void probarRecorridos() {
        ListaCircularDoble lista = new ListaCircularDoble();

        Cancion a = lista.agregarFinal("A", "Artista", 100);
        Cancion b = lista.agregarFinal("B", "Artista", 100);
        Cancion c = lista.agregarFinal("C", "Artista", 100);

        comprobar(lista.avanzar().getId() == b.getId(), "Avanzar a la siguiente canción");
        comprobar(lista.avanzar().getId() == c.getId(), "Avanzar a la tercera canción");
        comprobar(lista.avanzar().getId() == a.getId(), "Avanzar del final al inicio");

        comprobar(lista.retroceder().getId() == c.getId(),
                "Retroceder del inicio al final");
        comprobar(lista.retroceder().getId() == b.getId(),
                "Retroceder a la canción anterior");

        lista.verificarConsistencia();
    }

    // Revisar búsqueda y selección
    private static void probarSeleccionarYBuscar() {
        ListaCircularDoble lista = new ListaCircularDoble();

        Cancion a = lista.agregarFinal("A", "Artista", 100);
        Cancion b = lista.agregarFinal("B", "Artista", 100);

        comprobar(lista.seleccionar(b.getId()), "Seleccionar una canción existente");
        comprobar(lista.consultarActual().getId() == b.getId(),
                "La canción seleccionada queda como actual");
        comprobar(!lista.seleccionar(999999), "No seleccionar un ID inexistente");
        comprobar(lista.consultarActual().getId() == b.getId(),
                "La canción actual no cambia si el ID no existe");
        comprobar(lista.buscar(a.getId()).getId() == a.getId(),
                "Buscar devuelve la canción correcta");

        lista.verificarConsistencia();
    }

    // Revisar la eliminación de canciones
    private static void probarEliminarCanciones() {
        ListaCircularDoble lista = new ListaCircularDoble();

        Cancion a = lista.agregarFinal("A", "Artista", 100);
        Cancion b = lista.agregarFinal("B", "Artista", 100);
        Cancion c = lista.agregarFinal("C", "Artista", 100);

        comprobar(lista.eliminarPorId(999999) == null,
                "Eliminar un ID inexistente");
        comprobar(lista.getCantidad() == 3,
                "La lista no cambia al eliminar un ID inexistente");

        comprobar(lista.eliminarPorId(a.getId()).getId() == a.getId(),
                "Eliminar la primera canción");
        comprobar(lista.getCantidad() == 2, "Actualizar cantidad al eliminar");
        lista.verificarConsistencia();

        comprobar(lista.seleccionar(b.getId()), "Seleccionar canción antes de eliminar");
        comprobar(lista.eliminarActual().getId() == b.getId(),
                "Eliminar la canción actual");
        comprobar(lista.consultarActual().getId() == c.getId(),
                "La siguiente canción queda como actual");
        lista.verificarConsistencia();

        comprobar(lista.eliminarPorId(c.getId()).getId() == c.getId(),
                "Eliminar la última canción");
        comprobar(lista.getCantidad() == 0, "La lista queda vacía");
        comprobar(lista.consultarActual() == null, "No queda canción actual");
        lista.verificarConsistencia();

        ListaCircularDoble unaCancion = new ListaCircularDoble();
        Cancion unica = unaCancion.agregarFinal("Única", "Artista", 120);
        comprobar(unaCancion.eliminarActual().getId() == unica.getId(),
                "Eliminar la única canción");
        comprobar(unaCancion.getCantidad() == 0, "La lista de una canción queda vacía");
        unaCancion.verificarConsistencia();
    }

    // Revisar la simulación de reproducciones
    private static void probarReproduccion() {
        ListaCircularDoble lista = new ListaCircularDoble();

        Cancion a = lista.agregarFinal("A", "Artista", 100);
        Cancion b = lista.agregarFinal("B", "Artista", 100);
        Cancion c = lista.agregarFinal("C", "Artista", 100);

        ByteArrayOutputStream contenido = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(contenido, true, StandardCharsets.UTF_8);

        comprobar(lista.reproducir(5, salida), "Reproducir cinco canciones");
        comprobar(lista.consultarActual().getId() == c.getId(),
                "La reproducción deja la canción siguiente como actual");

        String texto = contenido.toString(StandardCharsets.UTF_8);
        comprobar(contar(texto, "Reproduciendo:") == 5,
                "Mostrar cinco reproducciones");

        lista.verificarConsistencia();

        boolean error = false;
        try {
            lista.reproducir(0, salida);
        } catch (IllegalArgumentException e) {
            error = true;
        }
        comprobar(error, "Rechazar cero reproducciones");
    }

    // Revisar que no se acepten datos incorrectos
    private static void probarDatosIncorrectos() {
        ListaCircularDoble lista = new ListaCircularDoble();

        boolean errorTitulo = false;
        try {
            lista.agregarFinal(" ", "Artista", 100);
        } catch (IllegalArgumentException e) {
            errorTitulo = true;
        }
        comprobar(errorTitulo, "Rechazar título vacío");

        boolean errorArtista = false;
        try {
            lista.agregarFinal("Canción", "", 100);
        } catch (IllegalArgumentException e) {
            errorArtista = true;
        }
        comprobar(errorArtista, "Rechazar artista vacío");

        boolean errorDuracion = false;
        try {
            lista.agregarFinal("Canción", "Artista", 0);
        } catch (IllegalArgumentException e) {
            errorDuracion = true;
        }
        comprobar(errorDuracion, "Rechazar duración no positiva");

        comprobar(lista.getCantidad() == 0,
                "Los datos incorrectos no agregan canciones");
        lista.verificarConsistencia();
    }

    // Contar cuántas veces aparece un texto
    private static int contar(String texto, String palabra) {
        int total = 0;
        int posicion = 0;

        while ((posicion = texto.indexOf(palabra, posicion)) != -1) {
            total++;
            posicion += palabra.length();
        }

        return total;
    }

    // Indicar si una prueba pasó
    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError("Falló la prueba: " + mensaje);
        }

        aprobadas++;
        System.out.println("Correcto: " + mensaje);
    }
}