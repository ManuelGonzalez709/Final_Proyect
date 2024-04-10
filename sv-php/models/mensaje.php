<?php
class Mensaje {
    private $id;
    private $id_emisor;
    private $id_receptor;
    private $mensaje;
    private $fecha;
    private $id_anuncio;

    public function __construct($id = null, $id_emisor = null, $id_receptor = null, $mensaje = null, $fecha = null, $id_anuncio = null) {
        // Si se proporcionan argumentos, asigna las propiedades
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

    public function getMensajesId($id_anuncio, $id_emisor, $id_receptor) {
        $sql = "SELECT * FROM mensaje WHERE id_anuncio = ? AND ((id_emisor = ? AND id_receptor = ?) OR (id_emisor = ? AND id_receptor = ?)) ORDER BY id";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("iiiii", $id_anuncio, $id_emisor, $id_receptor, $id_receptor, $id_emisor);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Obtiene el resultado de la consulta
        $result = $stmt->get_result();
        
        // Inicializa un array para almacenar los mensajes
        $mensajes = array();
    
        // Itera sobre los resultados y crea objetos Mensaje para cada uno
        while ($row = $result->fetch_assoc()) {
            $mensaje = new Mensaje(
                $row['id'],
                $row['id_emisor'],
                $row['id_receptor'],
                $row['mensaje'],
                $row['fecha'],
                $row['id_anuncio']
            );
            // Agrega el mensaje al array de mensajes
            $mensajes[] = $mensaje;
        }
    
        $stmt->close();
        $conexion->cerrarConexion();
        return $mensajes;
    }
}

?>