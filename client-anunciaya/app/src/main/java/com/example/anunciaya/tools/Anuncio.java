package com.example.anunciaya.tools;
/**
 * @Description Esto es una clase usada para la creación de Anuncios y para el RecyclerView (Mostrar los Anuncios)
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
public class Anuncio {
    /*Estos son los Atributos de la Clase*/
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
     * Este es el Constructor principal de la Clase
     */
    public Anuncio(){}

    /**
     * Esto es el otro constructor de la Clase que tiene todos los datos
     * @param id es un int
     * @param idUsuario es un int
     * @param idCategoria es un Int
     * @param titulo es un String
     * @param descripcion es un String
     * @param estado es un String
     * @param ubicacion es un String
     * @param precio es un String
     * @param fotos es un String
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
     * Esto es un metodo que se encarga de retornar el Id del Anuncio
     * @return returna un Int
     */
    public int getId() {return id;}

    /**
     * Esto es un metodo que se encarga de retornar el Id del Usuario
     * @return retorna un Int
     */
    public int getIdUsuario() {return idUsuario;}

    /**
     * Esto es un metodo que se encarga de retornar el Id de la categoria
     * @return retorna un Int
     */
    public int getidCategoria() {return idCategoria;}

    /**
     * Esto es un metodo que se ecarga de retornar el Titulo del Anuncio
     * @return retorna un String
     */
    public String getTitulo() {return titulo;}

    /**
     * Es un metodo que se encarga de retornar la Descripcion del Anuncio
     * @return retorna un String
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Esto es un metodo que se encarga de retornar el Estado del Anuncio
     * @return retorna un String
     */
    public String getEstado() {return estado;}

    /**
     * Esto es un metodo que se encarga de retornar la Ubicacion del Anuncio
     * @return retora un String
     */
    public String getUbicacion() {return ubicacion;}

    /**
     * Esto es un Metodo que se encarga de retornar el Precio del Anuncio
     * @return retorna un String
     */
    public String getPrecio() {return precio;}

    /**
     * Esto es un metodo que se encarga de retornar las Fotos del Anuncio
     * @return retorna un String
     */
    public String getFotos() {return fotos;}

    /**
     * Esto es un metodo que se encarga de establecer el Id del Anuncio
     * @param id contiene un Int
     */

    public void setId(int id) {this.id = id;}

    /**
     * Esto es un metodo que se encargada de establecer el Id del Usuario
     * @param idUsuario contiene un Int
     */
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    /**
     * Esto es un metodo que se encarga de establecer la categoria del Anuncio
     * @param idCategoria contiene un Int
     */
    public void setidCategoria(int idCategoria) {this.idCategoria = idCategoria;}

    /**
     * Es un metodo que se encarga de establecer el Titulo del Anuncio
     * @param titulo contiene un String
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Es un Metodo que se encarga de establecer la Descripcion del Anuncio
     * @param descripcion contiene un String
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    /**
     * Es un metodo que se encarga de establecer el Estado del Anuncio
     * @param estado contiene un String
     */
    public void setEstado(String estado) {this.estado = estado;}

    /**
     * Esto es un metodo que se encarga de establecer la Ubicacion del Anuncio
     * @param ubicacion contiene un String
     */
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    /**
     * Esto es un metodo que se encarga de Establecer el Precio del Anuncio
     * @param precio contiene un String
     */
    public void setPrecio(String precio) {this.precio = precio;}

    /**
     * Esto es un metodo que se encarga de establecer las fotos del Anuncio
     * @param fotos contiene un string
     */
    public void setFotos(String fotos) {this.fotos = fotos;}

}

