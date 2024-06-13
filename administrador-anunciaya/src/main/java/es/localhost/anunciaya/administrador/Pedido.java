package es.localhost.anunciaya.administrador;

/**
 * Clase que representa un Pedido en el sistema.
 *
 * @author AnunciaYa
 */
public class Pedido {
    /**
     * Identificador único del pedido.
     */
    private int id;

    /**
     * Nombre de usuario asociado al pedido.
     */
    private String nombUsu;

    /**
     * Identificador del anuncio relacionado con el pedido.
     */
    private int idAnuncio;

    /**
     * Título del anuncio asociado al pedido.
     */
    private String titulo;

    /**
     * Dirección de entrega del pedido.
     */
    private String direccion;

    /**
     * Ciudad de entrega del pedido.
     */
    private String ciudad;

    /**
     * Código postal de la ciudad de entrega del pedido.
     */
    private String cp;

    /**
     * Constructor de la clase Pedido.
     *
     * @param id          Identificador único del pedido.
     * @param nombUsu     Nombre de usuario asociado al pedido.
     * @param idAnuncio   Identificador del anuncio relacionado con el pedido.
     * @param titulo      Título del anuncio asociado al pedido.
     * @param direccion   Dirección de entrega del pedido.
     * @param ciudad      Ciudad de entrega del pedido.
     * @param cp          Código postal de la ciudad de entrega del pedido.
     */
    public Pedido(int id, String nombUsu, int idAnuncio, String titulo, String direccion, String ciudad, String cp) {
        this.id = id;
        this.nombUsu = nombUsu;
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.cp = cp;
    }

    /**
     * Método para obtener el identificador del pedido.
     *
     * @return El identificador del pedido.
     */
    public int getId() {return id;}

    /**
     * Método para obtener el nombre de usuario asociado al pedido.
     *
     * @return El nombre de usuario asociado al pedido.
     */
    public String getNombUsu() {return nombUsu;}

    /**
     * Método para obtener el identificador del anuncio asociado al pedido.
     *
     * @return El identificador del anuncio asociado al pedido.
     */
    public int getIdAnuncio() {return idAnuncio;}

    /**
     * Método para obtener el título del anuncio asociado al pedido.
     *
     * @return El título del anuncio asociado al pedido.
     */
    public String getTitulo() {return titulo;}

    /**
     * Método para obtener la dirección de entrega del pedido.
     *
     * @return La dirección de entrega del pedido.
     */
    public String getDireccion() {return direccion;}

    /**
     * Método para obtener la ciudad de entrega del pedido.
     *
     * @return La ciudad de entrega del pedido.
     */
    public String getCiudad() {return ciudad;}

    /**
     * Método para obtener el código postal de la ciudad de entrega del pedido.
     *
     * @return El código postal de la ciudad de entrega del pedido.
     */
    public String getCp() {return cp;}

    /**
     * Método para establecer el identificador del pedido.
     *
     * @param id El identificador del pedido.
     */
    public void setId(int id) {this.id = id;}
    /**
     * Método para establecer el nombre de usuario asociado al pedido.
     *
     * @param nombUsu El nombre de usuario asociado al pedido.
     */
    public void setNombUsu(String nombUsu) {this.nombUsu = nombUsu;}

    /**
     * Método para establecer el identificador del anuncio asociado al pedido.
     *
     * @param idAnuncio El identificador del anuncio asociado al pedido.
     */
    public void setIdAnuncio(int idAnuncio) {this.idAnuncio = idAnuncio;}

    /**
     * Método para establecer el título del anuncio asociado al pedido.
     *
     * @param titulo El título del anuncio asociado al pedido.
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Método para establecer la dirección de entrega del pedido.
     *
     * @param direccion La dirección de entrega del pedido.
     */
    public void setDireccion(String direccion) {this.direccion = direccion;}

    /**
     * Método para establecer la ciudad de entrega del pedido.
     *
     * @param ciudad La ciudad de entrega del pedido.
     */
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}

    /**
     * Método para establecer el código postal de la ciudad de entrega del pedido.
     *
     * @param cp El código postal de la ciudad de entrega del pedido.
     */
    public void setCp(String cp) {this.cp = cp;}
}
