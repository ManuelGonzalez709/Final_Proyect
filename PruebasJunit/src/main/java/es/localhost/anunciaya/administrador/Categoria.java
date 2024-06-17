package es.localhost.anunciaya.administrador;

/**
 * Clase que representa una categoría.ç
 *
 * @author AnunciaYa
 */
public class Categoria {

    /** Identificador único de la categoría. */
    private int id;

    /** Descripción de la categoría. */
    private String descripcion;

    /**
     * Constructor para crear una categoría con el id y la descripción especificados.
     *
     * @param id Identificador único de la categoría.
     * @param descripcion Descripción de la categoría.
     */
    public Categoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador único de la categoría.
     *
     * @return El identificador único de la categoría.
     */
    public int getId() {return id;}

    /**
     * Obtiene la descripción de la categoría.
     *
     * @return La descripción de la categoría.
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Establece el identificador único de la categoría.
     *
     * @param id El nuevo identificador único de la categoría.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Establece la descripción de la categoría.
     *
     * @param descripcion La nueva descripción de la categoría.
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
