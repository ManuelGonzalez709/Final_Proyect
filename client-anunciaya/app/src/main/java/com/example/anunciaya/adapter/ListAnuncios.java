/**
 * @Description Esto es una clase que ayuda a la creación de ventanas del RecyclerView
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 1.0
 */

package com.example.anunciaya.adapter;

import java.io.Serializable;

public class ListAnuncios implements Serializable {
    /*Atributos de la Clase*/
    private int idAnuncio;
    private String titulo;
    private String precio;
    private String ubicacion;
    private String direccion;
    private String ciudad;
    private String foto;

    /**
     * Constructor de la clase
     * @param idAnuncio es el Id del Anuncio que se va a guardar
     * @param titulo es el titulo del Anuncio que se va a guardar
     * @param precio es el Precio del Anuncio que se va a guardar
     * @param ubicacion es la Ubicacion del Anuncio que se va a guardar
     * @param foto es el String de las url de las fotos
     */
    public ListAnuncios(int idAnuncio, String titulo, String precio, String ubicacion, String foto) {
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.foto = foto;
    }
    /**
     * Constructor de la clase par la vista del Anuncio desde fuera (Antes de Hacer click)
     * @param titulo es el titulo del Anuncio que se va a guardar
     * @param precio es el Precio del Anuncio que se va a guardar
     * @param foto es el String de las url de las fotos
     */
    public ListAnuncios(String titulo, String precio, String direccion, String ciudad, String foto) {
        this.titulo = titulo;
        this.precio = precio;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.foto = foto;
    }

    /**
     * Esrto es un metodo que retorna el Id del Anuncio
     * @return retorna un entero
     */
    public int getIdAnuncio() {return idAnuncio;}

    /**
     * Esto es un metodo que retorna el Titulo del Anuncio
     * @return retorna un String
     */
    public String getTitulo() {return titulo;}

    /**
     * Esto es un metodo que retorna el Precio del Anuncio
     * @return retorna un String
     */
    public String getPrecio() {return precio;}

    /**
     * Esto es un metodo que retorna la Ubicacion del Anuncio
     * @return retorna un String
     */
    public String getUbicacion() {return ubicacion;}

    /**
     * Esto es un metodo que retorna la direccion del Anuncio
     * @return retorna un String
     */
    public String getDireccion() {return direccion;}

    /**
     * Esto es un metodo que retorna la Ciudad del Anuncio
     * @return retorna un String
     */
    public String getCiudad() {return ciudad;}

    /**
     * Esto es un metodo que retorna las fotos del Anuncio
     * @return retorna un String
     */
    public String getFoto() {return foto;}


    /**
     * Esto es un metodo que establece el Id del Anuncio
     * @param idAnuncio es un Integer/Entero
     */
    public void setIdAnuncio(int idAnuncio) {this.idAnuncio = idAnuncio;}

    /**
     * Esto es un metodo que establece el Titulo del Anuncio
     * @param titulo es un parametro de entrada que contiene un String
     */
    public void setTitulo(String titulo) {this.titulo = titulo;}

    /**
     * Esto es un metodo que se encarga de establecer el Precio del Anuncio
     * @param precio contiene un String
     */
    public void setPrecio(String precio) {this.precio = precio;}

    /**
     * Esto es un metodo que se encarga de establecer la Ubicacion del Anuncio
     * @param ubicacion contiene un String
     */
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    /**
     * Esto es un Metodo que se encarga de establecer la Direccion del Anuncio
     * @param direccion contiene un String
     */
    public void setDireccion(String direccion) {this.direccion = direccion;}

    /**
     * Esto es un metodo que se encarga de establecer la ciudad del Anuncio
     * @param ciudad contiene un String
     */
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}

    /**
     * Esto es un método que se encarga de establecer las Fotos del Anuncio
     * @param foto contiene un String que contiene las Fotos del Anuncio
     */
    public void setFoto(String foto) {this.foto = foto;}
}
