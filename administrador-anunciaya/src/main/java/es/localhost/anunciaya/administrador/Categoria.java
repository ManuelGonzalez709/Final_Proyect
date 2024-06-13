package es.localhost.anunciaya.administrador;

public class Categoria {
    private int id;
    private String descripcion;

    public Categoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {return id;}
    public String getDescripcion() {return descripcion;}

    public void setId(int id) {this.id = id;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
