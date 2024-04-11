<?php
require_once 'conexion.php';
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

    /**
     * Función que obtiene el pedido completo a partir del $id_pedido
     * 
     * return -> pedido
     */
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

    /**
     * Función que obtiene todos los pedidos que tiene un usuario por $id_usuario
     * 
     * return -> pedidos
     */
    public function getPedidosIdUsuario($id_usuario){
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
            $pedido = new Pedido(
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

    /**
     * Función que inserta un nuevo pedido, comprobando antes
     * si existe en la bd para no insertarlo de nuevo
     * 
     * return -> true / false
     */
    public function insertPedido($id_comprador, $id_anuncio){
        // Comprobar si ya existe el pedido en la bd
        $sql_check = "SELECT COUNT(*) AS total FROM pedido WHERE id_comprador = ? AND id_anuncio = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt_check = $conexion->obtenerConexion()->prepare($sql_check);
        
        $stmt_check->bind_param("ii", $id_comprador, $id_anuncio);
        
        // Ejecutar la consulta
        $stmt_check->execute();
        
        // Obtener el resultado de la consulta
        $resultado = $stmt_check->get_result();
        $fila = $resultado->fetch_assoc();
        
        // Verificar si ya existe un pedido con los mismos id_comprador e id_anuncio
        if ($fila['total'] > 0) {
            // Cerrar la consulta
            $stmt_check->close();
            // Cerrar la conexión
            $conexion->cerrarConexion();
            // Retornar false indicando que no se insertó el pedido porque ya existe
            return false;
        }
        
        // Si no existe, proceder con la inserción del nuevo pedido
        $sql_insert = "INSERT INTO pedido (id_comprador, id_anuncio) VALUES (?, ?)";
        
        $stmt_insert = $conexion->obtenerConexion()->prepare($sql_insert);
        
        $stmt_insert->bind_param("ii", $id_comprador, $id_anuncio);
        
        $stmt_insert->execute();
        
        // Verificar si la inserción fue exitosa
        $insercion_exitosa = $stmt_insert->affected_rows > 0;
        
        $stmt_insert->close();
        
        $conexion->cerrarConexion();
        
        return $insercion_exitosa;
    }
}

?>



