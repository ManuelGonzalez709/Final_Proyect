package com.example.anunciaya.tools;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String nombUsu;
    private String contras;
    private String fechaNacimiento;
    private String email;
    private String telefono;
    private String tipo;
    private  String foto;

    public Usuario(){}

    public Usuario(int id, String nombre, String apellidos, String nombUsu,
                   String contras, String fechaNacimiento, String email, String telefono,
                   String tipo, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombUsu = nombUsu;
        this.contras = contras;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
        this.foto = foto;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellidos() {return apellidos;}
    public String getNombUsu() {return nombUsu;}
    public String getContras() {return contras;}
    public String getFechaNacimiento() {return fechaNacimiento;}
    public String getEmail() {return email;}
    public String getTelefono() {return telefono;}
    public String getTipo() {return tipo;}
    public String getFoto() {return foto;}

    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public void setNombUsu(String nombUsu) {this.nombUsu = nombUsu;}
    public void setContras(String contras) {this.contras = contras;}
    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public void setEmail(String email) {this.email = email;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setFoto(String foto) {this.foto = foto;}
}
