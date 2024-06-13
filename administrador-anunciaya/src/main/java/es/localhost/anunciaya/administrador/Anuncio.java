package es.localhost.anunciaya.administrador;
public class Anuncio {
    private int id;
    private String nombUsu;
    private String categoria;
    private String titulo;
    private String descripcion;
    private String estado;
    private String ubicacion;
    private double precio;
    private String fotos;
    private String fechPubl;
    public Anuncio(){}
    public Anuncio(int id, String nombUsu, String categoria, String titulo, String descripcion,String estado, String ubicacion,
                   double precio, String fotos, String fechPubl) {
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
    }
    public int getId() {return id;}
    public String getNombUsu() {return nombUsu;}
    public String getCategoria() {return categoria;}
    public String getTitulo() {return titulo;}
    public String getDescripcion() {return descripcion;}
    public String getEstado() {return estado;}
    public String getUbicacion() {return ubicacion;}
    public double getPrecio() {return precio;}
    public String getFotos() {return fotos;}
    public String getFechPubl() {return fechPubl;}
    public void setId(int id) {this.id = id;}
    public void setNombUsu(String nombUsu) {this.nombUsu = nombUsu;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setTitulo(String titulo) {this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}
    public void setPrecio(double precio) {this.precio = precio; }
    public void setFotos(String fotos) {this.fotos = fotos;}
    public void setFechPubl(String fechPubl) {this.fechPubl = fechPubl;}
}
