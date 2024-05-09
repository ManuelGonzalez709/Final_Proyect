package com.example.anunciaya.adapter;

import java.io.Serializable;

public class ListAnuncios implements Serializable {
    private int idAnuncio;
    private String titulo;
    private String precio;
    private String ubicacion;
    private String foto;

    public ListAnuncios(String titulo, String precio, String ubicacion, String foto) {
        this.titulo = titulo;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.foto = foto;
    }

    public ListAnuncios(int idAnuncio, String titulo, String precio,String ubicacion, String foto) {
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.foto = foto;
    }

    public int getIdAnuncio() {return idAnuncio;}
    public String getTitulo() {return titulo;}
    public String getPrecio() {return precio;}
    public String getUbicacion() {return ubicacion;}
    public String getFoto() {return foto;}

    public void setIdAnuncio(int idAnuncio) {this.idAnuncio = idAnuncio;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setPrecio(String precio) {this.precio = precio;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}
    public void setFoto(String foto) {this.foto = foto;}
}
