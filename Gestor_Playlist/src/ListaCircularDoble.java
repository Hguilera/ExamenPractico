import java.io.PrintStream;

// TAD de lista doblemente enlazada circular //
public final class ListaCircularDoble {
    private Nodo inicio;
    private Nodo fin;
    private Nodo actual;
    private int cantidad;
    // No se reinicia al eliminar canciones ni al crear otra lista//
    private static long siguienteId = 1;

    private static long generarId() {
        if (siguienteId == Long.MAX_VALUE) {
            throw new IllegalStateException("Se agotaron los identificadores.");
        }
        return siguienteId++;
    }

    private Nodo crearNodo(String titulo, String artista, int segundos) {
        if (titulo == null || titulo.trim().isEmpty() || artista == null
                || artista.trim().isEmpty() || segundos <= 0) {
            throw new IllegalArgumentException("Título y artista obligatorios; duración positiva.");
        }
        if (cantidad == Integer.MAX_VALUE) {
            throw new IllegalStateException("Se alcanzó la capacidad del contador.");
        }
        return new Nodo(new Cancion(generarId(), titulo, artista, segundos));
    }

    // O(1)//
    public Cancion agregarInicio(String titulo, String artista, int segundos) {
        Nodo nuevo = crearNodo(titulo, artista, segundos);
        conectarNuevo(nuevo);
        inicio = nuevo;
        return nuevo.cancion;
    }

    public Cancion agregarFinal(String titulo, String artista, int segundos) {
        Nodo nuevo = crearNodo(titulo, artista, segundos);
        conectarNuevo(nuevo);
        fin = nuevo;
        return nuevo.cancion;
    }

    private void conectarNuevo(Nodo nuevo) {
        if (cantidad == 0) {
            nuevo.anterior = nuevo;
            nuevo.siguiente = nuevo;
            inicio = fin = actual = nuevo;
        } else {
            nuevo.anterior = fin;
            nuevo.siguiente = inicio;
            fin.siguiente = nuevo;
            inicio.anterior = nuevo;
        }
        cantidad++;
    }

    // O(n)//
    private Nodo buscarNodo(long id) {
        Nodo cursor = inicio;
        for (int i = 0; i < cantidad; i++) {
            if (cursor.cancion.getId() == id) return cursor;
            cursor = cursor.siguiente;
        }
        return null;
    }

    public Cancion buscar(long id) {
        Nodo encontrado = buscarNodo(id);
        return encontrado == null ? null : encontrado.cancion;
    }

    public boolean seleccionar(long id) {
        Nodo encontrado = buscarNodo(id);
        if (encontrado == null) return false;
        actual = encontrado;
        return true;
    }

    // O(n) buscar O(n), desconectar O(1)//
    public Cancion eliminarPorId(long id) {
        Nodo encontrado = buscarNodo(id);
        return encontrado == null ? null : desconectar(encontrado);
    }

    public Cancion eliminarActual() {
        return actual == null ? null : desconectar(actual);
    }

    private Cancion desconectar(Nodo eliminado) {
        Cancion resultado = eliminado.cancion;
        if (cantidad == 1) {
            inicio = fin = actual = null;
        } else {
            // Conservar vecinos y cambiar enlaces//
            Nodo previo = eliminado.anterior;
            Nodo posterior = eliminado.siguiente;
            previo.siguiente = posterior;
            posterior.anterior = previo;
            if (eliminado == inicio) inicio = posterior;
            if (eliminado == fin) fin = previo;
            if (eliminado == actual) actual = posterior;
        }
        cantidad--;
        eliminado.anterior = null;
        eliminado.siguiente = null;
        return resultado;
    }

    public Cancion consultarActual() {
        return actual == null ? null : actual.cancion;
    }

    public Cancion avanzar() {
        if (actual == null) return null;
        actual = actual.siguiente;
        return actual.cancion;
    }

    public Cancion retroceder() {
        if (actual == null) return null;
        actual = actual.anterior;
        return actual.cancion;
    }

    public int getCantidad() { return cantidad; }

    // O(n) contador //
    public void mostrarAdelante(PrintStream salida) {
        mostrar(salida, true);
    }

    public void mostrarAtras(PrintStream salida) {
        mostrar(salida, false);
    }

    private void mostrar(PrintStream salida, boolean adelante) {
        if (cantidad == 0) {
            salida.println("La lista está vacía.");
            return;
        }
        Nodo cursor = adelante ? inicio : fin;
        for (int i = 0; i < cantidad; i++) {
            salida.println(cursor.cancion + (cursor == actual ? " [ACTUAL]" : ""));
            cursor = adelante ? cursor.siguiente : cursor.anterior;
        }
    }

    // O(k) permite varias vueltas//
    public boolean reproducir(int k, PrintStream salida) {
        if (k <= 0) throw new IllegalArgumentException("k debe ser un entero positivo.");
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

    //Verificación O(n) usada SOLO en pruebas//
    public void verificarConsistencia() {
        if (cantidad == 0) {
            if (inicio != null || fin != null || actual != null) {
                throw new IllegalStateException("Referencias incorrectas en lista vacía.");
            }
            return;
        }
        if (cantidad < 0 || inicio == null || fin == null || actual == null
                || fin.siguiente != inicio || inicio.anterior != fin) {
            throw new IllegalStateException("Extremos o contador incorrectos.");
        }
        boolean actualEncontrado = false;
        Nodo cursor = inicio;
        Nodo ultimoVisitado = null;
        for (int i = 0; i < cantidad; i++) {
            if (cursor == null || (i > 0 && cursor == inicio)
                    || cursor.siguiente == null || cursor.anterior == null
                    || cursor.siguiente.anterior != cursor
                    || cursor.anterior.siguiente != cursor) {
                throw new IllegalStateException("Enlaces o cantidad inconsistentes.");
            }
            if (cursor == actual) actualEncontrado = true;
            ultimoVisitado = cursor;
            cursor = cursor.siguiente;
        }
        if (cursor != inicio || ultimoVisitado != fin || !actualEncontrado) {
            throw new IllegalStateException("Ciclo, final o canción actual incorrectos.");
        }
        cursor = fin;
        for (int i = 0; i < cantidad; i++) {
            if (i > 0 && cursor == fin) throw new IllegalStateException("Ciclo inverso corto.");
            cursor = cursor.anterior;
        }
        if (cursor != fin) throw new IllegalStateException("Ciclo inverso incorrecto.");
    }
}
