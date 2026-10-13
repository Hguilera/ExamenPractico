
 // Datos de una canción
public final class Cancion {

    private final long id;
    private final String titulo;
    private final String artista;
    private final int duracionSegundos;

    // Constructor de la canción
    Cancion(long id, String titulo, String artista, int duracionSegundos) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo.");
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }

        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("El artista no puede estar vacío.");
        }

        if (duracionSegundos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor que cero.");
        }

        this.id = id;
        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.duracionSegundos = duracionSegundos;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + titulo + " | " + artista
                + " | " + duracionSegundos + " s";
    }
}

