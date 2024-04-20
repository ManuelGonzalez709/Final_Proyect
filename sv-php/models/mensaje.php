<?php
require_once 'conexion.php';
class Mensaje {
    private $id;
    private $id_emisor;
    private $id_receptor;
    private $mensaje;
    private $fecha;
    private $id_anuncio;

    public function __construct($id = null, $id_emisor = null, $id_receptor = null, $mensaje = null, $fecha = null, $id_anuncio = null) {
        if ($id !== null && $id_receptor !== null && $id_receptor !== null && $mensaje !== null && $fecha !== null && $id_anuncio !== null) {
            $this->id = $id;
            $this->id_emisor = $id_emisor;
            $this->id_receptor = $id_receptor;
            $this->mensaje = $mensaje;
            $this->fecha = $fecha;
            $this->id_anuncio = $id_anuncio;
        }
    }

    public function getId() { return $this->id; }
    public function getIdEmisor() { return $this->id_emisor; }
    public function getIdReceptor() { return $this->id_receptor; }
    public function getMensaje() { return $this->mensaje; }
    public function getFecha() { return $this->fecha; }
    public function getIdAnuncio() { return $this->id_anuncio; }

    public function setId($id) { $this->id = $id; }
    public function setIdEmisor($id_emisor) { $this->id_emisor = $id_emisor; }
    public function setIdReceptor($id_receptor) { $this->id_receptor = $id_receptor; }
    public function setMensaje($mensaje) { $this->mensaje = $mensaje; }
    public function setFecha($fecha) { $this->fecha = $fecha; }
    public function setIdAnuncio($id_anuncio) { $this->id_anuncio = $id_anuncio; }

    /**
     * Función que obtiene los mensajes de un chat emisor y receptor de un anuncion
     * en concreto a partir de los parámetros $id_anuncio, $id_emisor, $id_receptor
     * 
     * return -> mensajes
     */
    public function getMensajesId($id_anuncio, $id_emisor, $id_receptor) {
        $sql = "SELECT * FROM mensaje WHERE id_anuncio = ? AND ((id_emisor = ? AND id_receptor = ?) OR (id_emisor = ? AND id_receptor = ?)) ORDER BY id";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("iiiii", $id_anuncio, $id_emisor, $id_receptor, $id_receptor, $id_emisor);
    
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Crear un array para almacenar los mensajes
        $mensajes = array();
        
        // Obtener todas los mensajes encontrados
        while ($row = $result->fetch_assoc()) {
            $mensajes[] = $row;
        }
        
        // Cerrar la conexión y devolver los mensajes como JSON
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Verificar si se encontraron mensajes
        if (empty($mensajes)) {
            return json_encode(array('error' => 'No se encontraron mensajes para los parámetros proporcionados.'));
        } else {
            return json_encode($mensajes);
        }
    }
    
    /**
     * Función que inserta nuevos mensajes en la base de datos a partir
     * del $id_emisor, $id_receptor, $mensaje, $id_anuncio.
     * 
     * return -> true / false
     */
    public function insertMensaje($id_emisor, $id_receptor, $mensaje, $id_anuncio){
        $sql = "INSERT INTO mensaje (id_emisor, id_receptor, mensaje, id_anuncio) VALUES (?, ?, ?, ?)";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("iisi", $id_emisor, $id_receptor, $mensaje, $id_anuncio);
        
        $stmt->execute();
        
        // Verifica si la inserción fue exitosa
        $insercion_exitosa = $stmt->affected_rows > 0;
        
        $stmt->close();
        $conexion->cerrarConexion();

        // Retorna true si se insertó correctamente, de lo contrario, retorna false
        return json_encode($insercion_exitosa);
    }
}

?>