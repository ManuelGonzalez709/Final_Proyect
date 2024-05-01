package com.example.anunciaya.tools;

public class Anuncio {
    private int id;
    private int idUsuario;
    private int idCategoria;
    private String titulo;
    private String descripcion;
    private String estado;
    private String ubicacion;
    private String precio;
    private String divisa;
    private String fotos;

    public Anuncio(){}

    public Anuncio(int id, int idUsuario, int idCategoria, String titulo, String descripcion,
                   String estado, String ubicacion, String precio, String divisa, String fotos) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.divisa = divisa;
        this.fotos = fotos;
    }

    public int getId() {return id;}
    public int getIdUsuario() {return idUsuario;}
    public int getidCategoria() {return idCategoria;}
    public String getTitulo() {return titulo;}
    public String getDescripcion() {return descripcion;}
    public String getEstado() {return estado;}
    public String getUbicacion() {return ubicacion;}
    public String getPrecio() {return precio;}
    public String getDivisa() {return divisa;}
    public String getFotos() {return fotos;}

    public void setId(int id) {this.id = id;}
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}
    public void setidCategoria(int idCategoria) {this.idCategoria = idCategoria;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}
    public void setPrecio(String precio) {this.precio = precio;}
    public void setDivisa(String divisa) {this.divisa = divisa;}
    public void setFotos(String fotos) {this.fotos = fotos;}

}

