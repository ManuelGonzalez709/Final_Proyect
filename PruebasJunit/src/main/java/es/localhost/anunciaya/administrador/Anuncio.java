package es.localhost.anunciaya.administrador;

/**
 * Clase que representa un anuncio.
 *
 * @author AnunciaYa
 */
public class Anuncio {

    /** Identificador único del anuncio. */
    private int id;

    /** Nombre de usuario del creador del anuncio. */
    private String nombUsu;

    /** Categoría del anuncio (por ejemplo, "Vehículos", "Inmuebles", etc.). */
    private String categoria;

    /** Título del anuncio. */
    private String titulo;

    /** Descripción detallada del anuncio. */
    private String descripcion;

    /** Estado del anuncio (por ejemplo, "Activo", "Inactivo", "Vendido"). */
    private String estado;

    /** Ubicación del artículo o servicio anunciado. */
    private String ubicacion;

    /** Precio del artículo o servicio anunciado. */
    private double precio;

    /** Ruta o URL de las fotos asociadas con el anuncio. */
    private String fotos;

    /** Fecha de publicación del anuncio en formato de cadena. */
    private String fechPubl;

    /**
     * El anuncio ha sido comprado o no.
     */
    private String isComprado;

    /**
     * Constructor por defecto para crear un anuncio vacío.
     */
    public Anuncio() {}

    /**
     * Constructor para crear un anuncio con todos los detalles especificados.
     *
     * @param id Identificador del anuncio.
     * @param nombUsu Nombre de usuario del creador del anuncio.
     * @param categoria Categoría del anuncio.
     * @param titulo Título del anuncio.
     * @param descripcion Descripción detallada del anuncio.
     * @param estado Estado del anuncio.
     * @param ubicacion Ubicación del artículo o servicio anunciado.
     * @param precio Precio del artículo o servicio anunciado.
     * @param fotos Ruta o URL de las fotos asociadas con el anuncio.
     * @param fechPubl Fecha de publicación del anuncio.
     * @param isComprado El anuncio se ha comprado.
     */
    public Anuncio(int id, String nombUsu, String categoria, String titulo, String descripcion, String estado,
                   String ubicacion, double precio, String fotos, String fechPubl, String isComprado) {
        this.id = id;
        this.nombUsu = nombUsu;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.fotos = fotos;
        this.fechPubl = fechPubl;
        this.isComprado = isComprado;
    }

    /**
     * Obtiene el identificador del anuncio.
     *
     * @return El identificador del anuncio.
     */
    public int getId() {return id;}

    /**
     * Obtiene el nombre de usuario del creador del anuncio.
     *
     * @return El nombre de usuario del creador del anuncio.
     */
    public String getNombUsu() {return nombUsu;}

    /**
     * Obtiene la categoría del anuncio.
     *
     * @return La categoría del anuncio.
     */
    public String getCategoria() {return categoria;}

    /**
     * Obtiene el título del anuncio.
     *
     * @return El título del anuncio.
     */
    public String getTitulo() {return titulo;}

    /**
     * Obtiene la descripción detallada del anuncio.
     *
     * @return La descripción del anuncio.
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Obtiene el estado del anuncio.
     *
     * @return El estado del anuncio.
     */
    public String getEstado() {return estado;}

    /**
     * Obtiene la ubicación del artículo o servicio anunciado.
     *
     * @return La ubicación del artículo o servicio anunciado.
     */
    public String getUbicacion() {return ubicacion;}

    /**
     * Obtiene el precio del artículo o servicio anunciado.
     *
     * @return El precio del artículo o servicio anunciado.
     */
    public double getPrecio() {return precio;}

    /**
     * Obtiene la ruta o URL de las fotos asociadas con el anuncio.
     *
     * @return La ruta o URL de las fotos del anuncio.
     */
    public String getFotos() {return fotos;}

    /**
     * Obtiene la fecha de publicación del anuncio.
     *
     * @return La fecha de publicación del anuncio.
     */
    public String getFechPubl() {return fechPubl;}


    /**
     * Obtiene si el anuncio se ha comprado.
     *
     * @return se ha comprado el anuncio.
     */
    public String getIsComprado() {return isComprado;}

    /**
     * Establece el identificador del anuncio.
     *
     * @param id El nuevo identificador del anuncio.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Establece el nombre de usuario del creador del anuncio.
     *
     * @param nombUsu El nuevo nombre de usuario del creador del anuncio.
     */
    public void setNombUsu(String nombUsu) {this.nombUsu = nombUsu;}

    /**
     * Establece la categoría del anuncio.
     *
     * @param categoria La nueva categoría del anuncio.
     */
    public void setCategoria(String categoria) {this.categoria = categoria;}

    /**
     * Establece el título del anuncio.
     *
     * @param titulo El nuevo título del anuncio.
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Establece la descripción del anuncio.
     *
     * @param descripcion La nueva descripción del anuncio.
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    /**
     * Establece el estado del anuncio.
     *
     * @param estado El nuevo estado del anuncio.
     */
    public void setEstado(String estado) {this.estado = estado;}

    /**
     * Establece la ubicación del artículo o servicio anunciado.
     *
     * @param ubicacion La nueva ubicación del artículo o servicio anunciado.
     */
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    /**
     * Establece el precio del artículo o servicio anunciado.
     *
     * @param precio El nuevo precio del artículo o servicio anunciado.
     */
    public void setPrecio(double precio) {this.precio = precio;}

    /**
     * Establece la ruta o URL de las fotos asociadas con el anuncio.
     *
     * @param fotos La nueva ruta o URL de las fotos del anuncio.
     */
    public void setFotos(String fotos) {this.fotos = fotos;}

    /**
     * Establece la fecha de publicación del anuncio.
     *
     * @param fechPubl La nueva fecha de publicación del anuncio.
     */
    public void setFechPubl(String fechPubl) {this.fechPubl = fechPubl;}

    /**
     * Establece si el anuncio se ha comprado o no.
     * @param isComprado Se ha comprado el anuncio.
     */
    public void setIsComprado(String isComprado) {this.isComprado = isComprado;}
}
