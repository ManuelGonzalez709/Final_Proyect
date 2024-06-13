package es.localhost.anunciaya.administrador;

/**
 * Clase que representa un usuario.
 *
 * @author AnunciaYa
 */
public class User {

    /** Identificador único del usuario. */
    private int Id;

    /** Nombre del usuario. */
    private String Nombre;

    /** Apellidos del usuario. */
    private String Apellidos;

    /** Nombre de usuario (nombre de usuario único). */
    private String nomb_usu;

    /** Fecha de nacimiento del usuario en formato String. */
    private String fecha_nacimiento;

    /** Dirección de correo electrónico del usuario. */
    private String email;

    /** Número de teléfono del usuario. */
    private String telefono;

    /** Tipo de usuario (ADM o STD). */
    private String tipo;

    /**
     * Constructor para crear un nuevo usuario con todos los detalles especificados.
     *
     * @param id Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param nomb_usu Nombre de usuario único.
     * @param fecha_nacimiento Fecha de nacimiento del usuario en formato String.
     * @param email Dirección de correo electrónico del usuario.
     * @param telefono Número de teléfono del usuario.
     * @param tipo Tipo de usuario (ADM o STD).
     */
    public User(int id, String nombre, String apellidos, String nomb_usu, String fecha_nacimiento, String email, String telefono, String tipo) {
        Id = id;
        Nombre = nombre;
        Apellidos = apellidos;
        this.nomb_usu = nomb_usu;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public int getId() {return Id;}

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El nuevo identificador único del usuario.
     */
    public void setId(int id) {Id = id;}

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {return Nombre;}

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {Nombre = nombre;}

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return Los apellidos del usuario.
     */
    public String getApellidos() {return Apellidos;}

    /**
     * Establece los apellidos del usuario.
     *
     * @param apellidos Los nuevos apellidos del usuario.
     */
    public void setApellidos(String apellidos) {Apellidos = apellidos;}

    /**
     * Obtiene el nombre de usuario único.
     *
     * @return El nombre de usuario único.
     */
    public String getNomb_usu() {return nomb_usu;}

    /**
     * Establece el nombre de usuario único.
     *
     * @param nomb_usu El nuevo nombre de usuario único.
     */
    public void setNomb_usu(String nomb_usu) {this.nomb_usu = nomb_usu;}

    /**
     * Obtiene la fecha de nacimiento del usuario en formato String.
     *
     * @return La fecha de nacimiento del usuario.
     */
    public String getFecha_nacimiento() {return fecha_nacimiento;}

    /**
     * Establece la fecha de nacimiento del usuario en formato String.
     *
     * @param fecha_nacimiento La nueva fecha de nacimiento del usuario.
     */
    public void setFecha_nacimiento(String fecha_nacimiento) {this.fecha_nacimiento = fecha_nacimiento;}

    /**
     * Obtiene la dirección de correo electrónico del usuario.
     *
     * @return La dirección de correo electrónico del usuario.
     */
    public String getEmail() {return email;}

    /**
     * Establece la dirección de correo electrónico del usuario.
     *
     * @param email La nueva dirección de correo electrónico del usuario.
     */
    public void setEmail(String email) {this.email = email;}

    /**
     * Obtiene el número de teléfono del usuario.
     *
     * @return El número de teléfono del usuario.
     */
    public String getTelefono() {return telefono;}

    /**
     * Establece el número de teléfono del usuario.
     *
     * @param telefono El nuevo número de teléfono del usuario.
     */
    public void setTelefono(String telefono) {this.telefono = telefono;}

    /**
     * Obtiene el tipo de usuario (ADM o STD).
     *
     * @return El tipo de usuario.
     */
    public String getTipo() {return tipo;}

    /**
     * Establece el tipo de usuario (ADM o STD).
     *
     * @param tipo El nuevo tipo de usuario.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
