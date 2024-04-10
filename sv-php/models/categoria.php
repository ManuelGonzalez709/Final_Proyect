<?php
class Categoria {
    private $id;
    private $descripcion;

    public function __construct($id = null, $descripcion = null){
        if($id != null && $descripcion != null){
            $this->id = $id;
            $this->descripcion = $descripcion;
        }
    } 

    public function getId() { return $this->id; }
    public function getDescripcion() { return $this->descripcion; }

    public function setId($id) { $this->id = $id; }
    public function setDescripcion($descripcion) { $this->descripcion = $descripcion; }

    public function getCategoriaId($id_categoria){
        $sql = "SELECT * FROM categoria WHERE id = ?";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_categoria);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Obtiene el resultado de la consulta
        $result = $stmt->get_result();
        
        // Obtiene la categoría encontrada como objeto
        $categoria = null;
        if ($row = $result->fetch_assoc()) {
            $categoria = new Categoria(
                $row['id'],
                $row['descripcion']
            );
        }
    
        $stmt->close();
        $conexion->cerrarConexion();
        return $categoria;
    }
    
}

?>