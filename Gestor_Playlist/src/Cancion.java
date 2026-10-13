//Datos de una canción//
public final class Cancion {
    private final long id;
    private final String titulo;
    private final String artista;
    private final int duracionSegundos;

    Cancion(long id, String titulo, String artista, int duracionSegundos) {
        if (id <= 0 || titulo == null || titulo.trim().isEmpty()
                || artista == null || artista.trim().isEmpty() || duracionSegundos <= 0) {
            throw new IllegalArgumentException("Los datos de la canción son inválidos.");
        }
        this.id = id;
        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.duracionSegundos = duracionSegundos;
    }

    public long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracionSegundos() { return duracionSegundos; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + titulo + " | " + artista
                + " | " + duracionSegundos + " s";
    }
}
