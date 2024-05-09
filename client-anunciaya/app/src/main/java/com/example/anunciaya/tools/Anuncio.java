package com.example.anunciaya.tools;
/**
 * Clase Anuncio
 * @Author Carlos Murillo Perez & Manuel Gonzalez Perez
 * @Version 2.1
 */

public class Anuncio {
    // Atributos del Anuncio
    private int id;
    private int idUsuario;
    private int idCategoria;
    private String titulo;
    private String descripcion;
    private String estado;
    private String ubicacion;
    private String precio;
    private String fotos;

    /**
     * Constructor Vacio
     */
    public Anuncio(){}

    /**
     * Constructor con todos los parametros
     * @param id
     * @param idUsuario
     * @param idCategoria
     * @param titulo
     * @param descripcion
     * @param estado
     * @param ubicacion
     * @param precio
     * @param fotos
     */

    public Anuncio(int id, int idUsuario, int idCategoria, String titulo, String descripcion,
                   String estado, String ubicacion, String precio, String fotos) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.fotos = fotos;
    }

    /**
     * Metodo get
     * @return retorna el Id del Anuncio
     */
    public int getId() {return id;}

    /**
     * Metodo get
     * @return retorna el Id del usuario
     */
    public int getIdUsuario() {return idUsuario;}

    /**
     * Metodo get
     * @return retorna el Id de la Categoria
     */
    public int getidCategoria() {return idCategoria;}

    /**
     * Metodo get
     * @return retorna el titulo del anuncio
     */
    public String getTitulo() {return titulo;}

    /**
     * Metodo get
     * @return retorna la Descripcion del Anuncio
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Metodo get
     * @return retorna el Estado del Anuncio
     */
    public String getEstado() {return estado;}

    /**
     * Metodo get
     * @return retorna la Ubicacion del Anuncio
     */
    public String getUbicacion() {return ubicacion;}

    /**
     * Metodo get
     * @return retorna el Precio
     */
    public String getPrecio() {return precio;}

    /**
     * Metodo get
     * @return retorna las Fotos
     */
    public String getFotos() {return fotos;}

    /**
     * Metodo set
     * @param id establece el Id del Anuncio
     */

    public void setId(int id) {this.id = id;}

    /**
     * Metodo set
     * @param idUsuario establece el id del Usuario
     */
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    /**
     * Metodo set
     * @param idCategoria establece el id de Categoria
     */
    public void setidCategoria(int idCategoria) {this.idCategoria = idCategoria;}

    /**
     * Metodo set
     * @param titulo establece el titulo del anuncio
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Metodo set
     * @param descripcion establece la Descripcion del Anuncio
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    /**
     * Metodo set
     * @param estado establece el Estado del Anuncio
     */
    public void setEstado(String estado) {this.estado = estado;}

    /**
     * Metodo set
     * @param ubicacion establece la Ubicacion del Anuncio
     */
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    /**
     * Metodo set
     * @param precio establece el Precio del Anuncio
     */
    public void setPrecio(String precio) {this.precio = precio;}

    /**
     * Metodo set
     * @param fotos establece las Fotos del Anuncio
     */
    public void setFotos(String fotos) {this.fotos = fotos;}

}

