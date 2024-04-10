<?php
class Mensaje {
    private $id;
    private $id_chat;
    private $id_comprador;
    private $id_vendedor;
    private $mensaje;
    private $fecha;
    private $id_anuncio;

    public function __construct($id = null, $id_chat = null, $id_comprador = null, $id_vendedor = null, $mensaje = null, $fecha = null, $id_anuncio = null) {
        // Si se proporcionan argumentos, asigna las propiedades
        if ($id !== null && $id_chat !== null && $id_comprador !== null && $id_vendedor !== null && $mensaje !== null && $fecha !== null && $id_anuncio !== null) {
            $this->id = $id;
            $this->id_chat = $id_chat;
            $this->id_comprador = $id_comprador;
            $this->id_vendedor = $id_vendedor;
            $this->mensaje = $mensaje;
            $this->fecha = $fecha;
            $this->id_anuncio = $id_anuncio;
        }
    }

    public function getId() { return $this->id; }
    public function getIdChat() { return $this->id_chat; }
    public function getIdComprador() { return $this->id_comprador; }
    public function getIdVendedor() { return $this->id_vendedor; }
    public function getMensaje() { return $this->mensaje; }
    public function getFecha() { return $this->fecha; }
    public function getIdAnuncio() { return $this->id_anuncio; }

    public function setId($id) { $this->id = $id; }
    public function setIdChat($id_chat) { $this->id_chat = $id_chat; }
    public function setIdComprador($id_comprador) { $this->id_comprador = $id_comprador; }
    public function setIdVendedor($id_vendedor) { $this->id_vendedor = $id_vendedor; }
    public function setMensaje($mensaje) { $this->mensaje = $mensaje; }
    public function setFecha($fecha) { $this->fecha = $fecha; }
    public function setIdAnuncio($id_anuncio) { $this->id_anuncio = $id_anuncio; }

    public function obtenerMensajesChat($id_anuncio, $id_emisor, $id_receptor) {
        $sql = "SELECT mensaje FROM mensaje WHERE id_anuncio = ? AND ((id_emisor = ? AND id_receptor = ?) OR (id_emisor = ? AND id_receptor = ?)) ORDER BY id";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular los parámetros
        $stmt->bind_param("iiiii", $id_anuncio, $id_emisor, $id_receptor, $id_receptor, $id_emisor);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Crear un array para almacenar los mensajes
        $mensajes = [];
        
        // Iterar sobre los resultados y crear objetos Mensaje
        while ($row = $result->fetch_assoc()) {
            // Agregar el mensaje al array de mensajes
            $mensajes[] = $row['mensaje'];
        }
        
        // Cerrar la conexión y devolver los mensajes
        $stmt->close();
        $conexion->cerrarConexion();
        return $mensajes;
    }
    
    
    
    
}

?>