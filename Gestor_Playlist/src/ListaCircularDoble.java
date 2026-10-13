
import java.io.PrintStream;

// Lista doblemente enlazada circular
public final class ListaCircularDoble {

    private Nodo inicio;
    private Nodo fin;
    private Nodo actual;
    private int cantidad;

    // El ID sigue aumentando aunque se eliminen canciones
    private static long siguienteId = 1;

    private static long generarId() {
        if (siguienteId == Long.MAX_VALUE) {
            throw new IllegalStateException("Se agotaron los identificadores.");
        }

        return siguienteId++;
    }

    // Crear un nodo con los datos de la canción
    private Nodo crearNodo(String titulo, String artista, int segundos) {
        if (titulo == null || titulo.trim().isEmpty()
                || artista == null || artista.trim().isEmpty()
                || segundos <= 0) {
            throw new IllegalArgumentException(
                    "El título y el artista son obligatorios y la duración debe ser positiva.");
        }

        if (cantidad == Integer.MAX_VALUE) {
            throw new IllegalStateException("Se alcanzó el límite de canciones.");
        }

        Cancion cancion = new Cancion(generarId(), titulo, artista, segundos);
        return new Nodo(cancion);
    }

    // Agregar una canción al inicio: O(1)
    public Cancion agregarInicio(String titulo, String artista, int segundos) {
        Nodo nuevo = crearNodo(titulo, artista, segundos);
        conectarNuevo(nuevo);
        inicio = nuevo;

        return nuevo.cancion;
    }

    // Agregar una canción al final: O(1)
    public Cancion agregarFinal(String titulo, String artista, int segundos) {
        Nodo nuevo = crearNodo(titulo, artista, segundos);
        conectarNuevo(nuevo);
        fin = nuevo;

        return nuevo.cancion;
    }

    // Conectar el nuevo nodo con los demás
    private void conectarNuevo(Nodo nuevo) {
        if (cantidad == 0) {
            nuevo.anterior = nuevo;
            nuevo.siguiente = nuevo;

            inicio = nuevo;
            fin = nuevo;
            actual = nuevo;
        } else {
            nuevo.anterior = fin;
            nuevo.siguiente = inicio;

            fin.siguiente = nuevo;
            inicio.anterior = nuevo;
        }

        cantidad++;
    }

    // Buscar una canción por su ID: O(n)
    private Nodo buscarNodo(long id) {
        Nodo cursor = inicio;

        for (int i = 0; i < cantidad; i++) {
            if (cursor.cancion.getId() == id) {
                return cursor;
            }

            cursor = cursor.siguiente;
        }

        return null;
    }

    public Cancion buscar(long id) {
        Nodo encontrado = buscarNodo(id);

        if (encontrado == null) {
            return null;
        }

        return encontrado.cancion;
    }

    // Seleccionar una canción como actual: O(n)
    public boolean seleccionar(long id) {
        Nodo encontrado = buscarNodo(id);

        if (encontrado == null) {
            return false;
        }

        actual = encontrado;
        return true;
    }

    // Buscar y eliminar una canción por ID: O(n)
    public Cancion eliminarPorId(long id) {
        Nodo encontrado = buscarNodo(id);

        if (encontrado == null) {
            return null;
        }

        return desconectar(encontrado);
    }

    // Eliminar la canción actual: O(1)
    public Cancion eliminarActual() {
        if (actual == null) {
            return null;
        }

        return desconectar(actual);
    }

    // Desconectar un nodo de la lista
    private Cancion desconectar(Nodo eliminado) {
        Cancion cancionEliminada = eliminado.cancion;

        if (cantidad == 1) {
            inicio = null;
            fin = null;
            actual = null;
        } else {
            Nodo previo = eliminado.anterior;
            Nodo posterior = eliminado.siguiente;

            previo.siguiente = posterior;
            posterior.anterior = previo;

            if (eliminado == inicio) {
                inicio = posterior;
            }

            if (eliminado == fin) {
                fin = previo;
            }

            if (eliminado == actual) {
                actual = posterior;
            }
        }

        cantidad--;

        // El nodo eliminado ya no apunta a otros nodos
        eliminado.anterior = null;
        eliminado.siguiente = null;

        return cancionEliminada;
    }

    // Consultar la canción actual: O(1)
    public Cancion consultarActual() {
        if (actual == null) {
            return null;
        }

        return actual.cancion;
    }

    // Avanzar a la siguiente canción: O(1)
    public Cancion avanzar() {
        if (actual == null) {
            return null;
        }

        actual = actual.siguiente;
        return actual.cancion;
    }

    // Retroceder a la canción anterior: O(1)
    public Cancion retroceder() {
        if (actual == null) {
            return null;
        }

        actual = actual.anterior;
        return actual.cancion;
    }

    // Consultar la cantidad de canciones: O(1)
    public int getCantidad() {
        return cantidad;
    }

    // Mostrar las canciones desde el inicio: O(n)
    public void mostrarAdelante(PrintStream salida) {
        mostrar(salida, true);
    }

    // Mostrar las canciones desde el final: O(n)
    public void mostrarAtras(PrintStream salida) {
        mostrar(salida, false);
    }

    // Recorrer la lista sin repetir canciones
    private void mostrar(PrintStream salida, boolean adelante) {
        if (cantidad == 0) {
            salida.println("La lista está vacía.");
            return;
        }

        Nodo cursor;

        if (adelante) {
            cursor = inicio;
        } else {
            cursor = fin;
        }

        for (int i = 0; i < cantidad; i++) {
            salida.println(cursor.cancion
                    + (cursor == actual ? " [ACTUAL]" : ""));

            if (adelante) {
                cursor = cursor.siguiente;
            } else {
                cursor = cursor.anterior;
            }
        }
    }

    // Simular k reproducciones: O(k)
    public boolean reproducir(int k, PrintStream salida) {
        if (k <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de reproducciones debe ser positiva.");
        }

        if (actual == null) {
            salida.println("La lista está vacía.");
            return false;
        }

        for (int i = 0; i < k; i++) {
            salida.println("Reproduciendo: " + actual.cancion);
            actual = actual.siguiente;
        }

        return true;
    }

    // Comprobar que los enlaces y el contador sean correctos
    // Este método se utiliza en las pruebas: O(n)
    public void verificarConsistencia() {
        if (cantidad == 0) {
            if (inicio != null || fin != null || actual != null) {
                throw new IllegalStateException(
                        "Las referencias deben ser null cuando la lista está vacía.");
            }

            return;
        }

        if (cantidad < 0 || inicio == null || fin == null || actual == null) {
            throw new IllegalStateException(
                    "Las referencias de la lista son incorrectas.");
        }

        if (fin.siguiente != inicio || inicio.anterior != fin) {
            throw new IllegalStateException(
                    "Los extremos de la lista no están conectados.");
        }

        boolean actualEncontrado = false;
        Nodo cursor = inicio;
        Nodo ultimoVisitado = null;

        for (int i = 0; i < cantidad; i++) {
            if (cursor == null || cursor.siguiente == null
                    || cursor.anterior == null
                    || cursor.siguiente.anterior != cursor
                    || cursor.anterior.siguiente != cursor) {
                throw new IllegalStateException(
                        "Los enlaces de la lista son incorrectos.");
            }

            if (cursor == actual) {
                actualEncontrado = true;
            }

            ultimoVisitado = cursor;
            cursor = cursor.siguiente;
        }

        if (cursor != inicio || ultimoVisitado != fin || !actualEncontrado) {
            throw new IllegalStateException(
                    "El ciclo, el final o la canción actual son incorrectos.");
        }

        cursor = fin;

        for (int i = 0; i < cantidad; i++) {
            cursor = cursor.anterior;
        }

        if (cursor != fin) {
            throw new IllegalStateException(
                    "El recorrido en sentido inverso es incorrecto.");
        }
    }
}
