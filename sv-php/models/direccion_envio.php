<?php
class DireccionEnvio {
    private $id;
    private $direccion;
    private $cp;
    private $provincia;
    private $poblacion;
    private $pais;
    private $id_usuario;
    
    public function __construct($id = null, $direccion = null, $cp = null, $provincia = null, $poblacion = null, $pais = null, $id_usuario = null) {
        // Si se proporcionan argumentos, asigna las propiedades
        if ($id !== null && $direccion !== null && $cp !== null && $provincia !== null && $poblacion !== null && $pais !== null && $id_usuario !== null) {
            $this->id = $id;
            $this->direccion = $direccion;
            $this->cp = $cp;
            $this->provincia = $provincia;
            $this->poblacion = $poblacion;
            $this->pais = $pais;
            $this->id_usuario = $id_usuario;
        }
    }
    
    public function getId() { return $this->id; }
    public function getDireccion() { return $this->direccion; }
    public function getCp() { return $this->cp; }
    public function getProvincia() { return $this->provincia; }
    public function getPoblacion() { return $this->poblacion; }
    public function getPais() { return $this->pais; }
    public function getIdUsuario() { return $this->id_usuario; }

    public function setDireccion($direccion) { $this->direccion = $direccion; }
    public function setCp($cp) { $this->cp = $cp; }
    public function setProvincia($provincia) { $this->provincia = $provincia; }
    public function setPoblacion($poblacion) { return $this->poblacion = $poblacion; }
    public function setPais($pais) { $this->pais = $pais; }
    public function setIdUsuario($id_usuario) { $this->id_usuario = $id_usuario; }

    /**
     * Función que obtiene todas las direcciones de envío de un usuario
     * pasado como parámetro $id_usuario
     * 
     * return direcciones_envio
     */
    public static function getDirEnvioIdUsuario($id_usuario) {
        $sql = "SELECT * FROM direccion_envio WHERE id_usuario = ?";
        
        // Preparar la consulta
        $conexion = new Conexion();
        $conexion->conectar();

        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular parámetros
        $stmt->bind_param("i", $id_usuario);
        
        $stmt->execute();
        
        // Obtiene los resultados
        $result = $stmt->get_result();
        
        // Array para almacenar las direcciones de envío
        $direcciones = array();
        
        while ($row = $result->fetch_assoc()) {
            $direccion = new DireccionEnvio(
                $row['id'],
                $row['direccion'],
                $row['cp'],
                $row['provincia'],
                $row['poblacion'],
                $row['pais'],
                $row['id_usuario']
            );
            $direcciones[] = $direccion;
        }
        
        $stmt->close();
        
        return $direcciones;
    }

    /**
     * Función que inserta nuevas direcciones de envío de un usuario en específico
     * en la base de datos a partir de los parámetros $direccion, $cp, $provincia, 
     * $poblacion, $pais, $id_usuario
     * 
     * return -> true / false
     */
    public function insertDirEnvio($direccion, $cp, $provincia, $poblacion, $pais, $id_usuario) {
        // Query SQL para insertar una nueva dirección en la base de datos
        $sql = "INSERT INTO direccion_envio (direccion, cp, provincia, poblacion, pais, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Prepara la sentencia SQL
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Enlaza los parámetros con los valores proporcionados
        $stmt->bind_param("sssssi", $direccion, $cp, $provincia, $poblacion, $pais, $id_usuario);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Verifica si la inserción fue exitosa
        $insercion_exitosa = $stmt->affected_rows > 0;
        
        $stmt->close();
        $conexion->cerrarConexion();

        // Retorna true si se insertó correctamente, de lo contrario, retorna false
        return $insercion_exitosa;
    }
}

?>
