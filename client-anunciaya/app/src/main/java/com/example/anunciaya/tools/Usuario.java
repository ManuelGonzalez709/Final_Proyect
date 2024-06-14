package com.example.anunciaya.tools;
/**
 * @Description Esto es una clase usada para mostrar los datos y modificar el usuario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 1.0
 */
public class Usuario {
    /*Estos son los Atributos de la clase*/
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

    /**
     * Este es el constructor principal de la clase¡
     */
    public Usuario(){}

    /**
     * Este es el constructor que tiene todos los parametros
     * @param id contiene el id del Usuario
     * @param nombre contiene el Nombre del Usuario
     * @param apellidos contiene los Apellidos del Usuario
     * @param nombUsu contiene el Nombre de Usuario
     * @param contras contiene la contraseña del Usuario
     * @param fechaNacimiento contiene la fecha de nacimiento del Usuario
     * @param email contiene el Email del Usuario
     * @param telefono contiene el Telefono del Usuario
     * @param tipo contiene el Tipo del Usuario
     * @param foto contiene la Foto del Usuario
     */
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

    /**
     * Este es un método que se encarga de obtener el ID.
     * @return retorna un int.
     */
    public int getId() {return id;}

    /**
     * Este es un método que se encarga de obtener el Nombre.
     * @return retorna un String.
     */
    public String getNombre() {return nombre;}

    /**
     * Este es un método que se encarga de obtener los Apellidos.
     * @return retorna un String.
     */
    public String getApellidos() {return apellidos;}

    /**
     * Este es un método que se encarga de obtener el Nombre de Usuario.
     * @return retorna un String.
     */
    public String getNombUsu() {return nombUsu;}

    /**
     * Este es un método que se encarga de obtener la Contraseña.
     * @return retorna un String.
     */
    public String getContras() {return contras;}

    /**
     * Este es un método que se encarga de obtener la Fecha de Nacimiento.
     * @return retorna un String.
     */
    public String getFechaNacimiento() {return fechaNacimiento;}

    /**
     * Este es un método que se encarga de obtener el Email.
     * @return retorna un String.
     */
    public String getEmail() {return email;}

    /**
     * Este es un método que se encarga de obtener el Teléfono.
     * @return retorna un String.
     */
    public String getTelefono() {return telefono;}

    /**
     * Este es un método que se encarga de obtener el Tipo.
     * @return retorna un String.
     */
    public String getTipo() {return tipo;}

    /**
     * Este es un método que se encarga de obtener la Foto.
     * @return retorna un String.
     */
    public String getFoto() {return foto;}

    /**
     * Este es un método que se encarga de establecer el ID.
     * @param id el ID a establecer.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Este es un método que se encarga de establecer el Nombre.
     * @param nombre el Nombre a establecer.
     */
    public void setNombre(String nombre) {this.nombre = nombre;}

    /**
     * Este es un método que se encarga de establecer los Apellidos.
     * @param apellidos los Apellidos a establecer.
     */
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}

    /**
     * Este es un método que se encarga de establecer el Nombre de Usuario.
     * @param nombUsu el Nombre de Usuario a establecer.
     */
    public void setNombUsu(String nombUsu) {this.nombUsu = nombUsu;}

    /**
     * Este es un método que se encarga de establecer la Contraseña.
     * @param contras la Contraseña a establecer.
     */
    public void setContras(String contras) {this.contras = contras;}

    /**
     * Este es un método que se encarga de establecer la Fecha de Nacimiento.
     * @param fechaNacimiento la Fecha de Nacimiento a establecer.
     */
    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    /**
     * Este es un método que se encarga de establecer el Email.
     * @param email el Email a establecer.
     */
    public void setEmail(String email) {this.email = email;}

    /**
     * Este es un método que se encarga de establecer el Teléfono.
     * @param telefono el Teléfono a establecer.
     */
    public void setTelefono(String telefono) {this.telefono = telefono;}

    /**
     * Este es un método que se encarga de establecer el Tipo.
     * @param tipo el Tipo a establecer.
     */
    public void setTipo(String tipo) {this.tipo = tipo;}

    /**
     * Este es un método que se encarga de establecer la Foto.
     * @param foto la Foto a establecer.
     */
    public void setFoto(String foto) {this.foto = foto;}
}
