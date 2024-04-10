<?php
class Pedido {
    private $id;
    private $id_anuncio;
    private $id_comprador;
    private $fecha;

    public function __construct($id = null, $id_anuncio = null, $id_usuario = null, $fecha = null) {
        if($id != null && $id_anuncio != null && $id_usuario != null && $fecha != null){
            $this->id = $id;
            $this->id_anuncio = $id_anuncio;
            $this->id_comprador = $id_usuario;
            $this->fecha = $fecha;
        }
        
    }

    public function getId() { return $this->id; }
    public function getIdAnuncio() { return $this->id_anuncio; }
    public function getIdComprador() { return $this->id_comprador; }
    public function getFecha() { return $this->fecha; }

    public function setId($id) { $this->id = $id; }
    public function setIdAnuncio($id_anuncio) { $this->id_anuncio = $id_anuncio; }
    public function setIdComprador($id_usuario) { $this->id_comprador = $id_usuario; }
    public function setFecha($fecha) { $this->fecha = $fecha; }

    public function getPedidoId($id_pedido){
        $sql = "SELECT * FROM pedido WHERE id = ?";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_pedido);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Obtiene el resultado de la consulta
        $result = $stmt->get_result();
        
        // Obtiene el pedido encontrado como objeto
        $pedido = null;
        if ($row = $result->fetch_assoc()) {
            $pedido = new Pedido(
                $row['id'],
                $row['id_anuncio'],
                $row['id_comprador'],
                $row['fech_pedido']
            );
        }
    
        $stmt->close();
        $conexion->cerrarConexion();
        return $pedido;
    }

    public function getPedidoIdUsuario($id_usuario){ // DA FALLO
        $sql = "SELECT * FROM pedido WHERE id_comprador = ?";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_usuario);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Obtiene el resultado de la consulta
        $result = $stmt->get_result();
        
        // Inicializa un array para almacenar los pedidos
        $pedidos = array();
        
        // Itera sobre los resultados y crea objetos Pedido para cada uno
        while ($row = $result->fetch_assoc()) {
            $pedido = new Anuncio(
                $row['id'],
                $row['id_anuncio'],
                $row['id_comprador'],
                $row['fech_pedido']
            );
            // Agrega el pedido al array de pedidos
            $pedidos[] = $pedido;
        }
    
        $stmt->close();
        $conexion->cerrarConexion();
        return $pedidos;
    }
}

?>



