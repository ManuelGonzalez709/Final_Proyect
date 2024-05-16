package es.localhost.anunciaya.administrador.commons;

public class User {
    private int Id;
    private String Nombre,Apellidos,nomb_usu,contrasena,fecha_nacimiento,email,telefono,tipo;

    public User(int id, String nombre, String apellidos, String nomb_usu, String contrasena, String fecha_nacimiento, String email, String telefono, String tipo) {
        Id = id;
        Nombre = nombre;
        Apellidos = apellidos;
        this.nomb_usu = nomb_usu;
        this.contrasena = contrasena;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNomb_usu() {
        return nomb_usu;
    }

    public void setNomb_usu(String nomb_usu) {
        this.nomb_usu = nomb_usu;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
