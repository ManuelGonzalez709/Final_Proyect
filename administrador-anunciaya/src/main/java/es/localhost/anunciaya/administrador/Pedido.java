package es.localhost.anunciaya.administrador;

public class Pedido {
    private int id;
    private String nombUsu;
    private int idAnuncio;
    private String titulo;
    private String direccion;
    private String ciudad;
    private String cp;


    public Pedido(int id, String nombUsu, int idAnuncio, String titulo, String direccion, String ciudad, String cp) {
        this.id = id;
        this.nombUsu = nombUsu;
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.cp = cp;
    }

    public int getId() {return id;}
    public String getNombUsu() {return nombUsu;}
    public int getIdAnuncio() {return idAnuncio;}
    public String getTitulo() {return titulo;}
    public String getDireccion() {return direccion;}
    public String getCiudad() {return ciudad;}
    public String getCp() {return cp;}

    public void setId(int id) {this.id = id;}
    public void setNombUsu(String nombUsu) {this.nombUsu = nombUsu;}
    public void setIdAnuncio(int idAnuncio) {this.idAnuncio = idAnuncio;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
    public void setCp(String cp) {this.cp = cp;}
}
