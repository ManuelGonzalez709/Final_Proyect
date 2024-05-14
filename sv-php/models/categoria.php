<?php
require_once 'conexion.php';

class Categoria {
    /**
     * Función que obtiene el id categoria
     * a partir de $descripcion
     * 
     * return categoria
     */
    public function getCategoriaDescripcion($id_categoria){
        $sql = "SELECT descripcion FROM categoria WHERE id = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular los parámetros
        $stmt->bind_param("i", $id_categoria);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Obtener la descripción de la categoría
        $descripcion = $result->fetch_assoc();
        
        // Cerrar la conexión
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Verificar si se encontró la categoría y devolver la descripción o null
        if ($descripcion) {
            return json_encode($descripcion);
        } else {
            return json_encode(array("success" => false, "data" => "null"));
        }
    }
    

    /**
     * Función que obtiene el id de una categoria
     * a partir de la descripcion
     * 
     * return id_categoria
     */
    public function getCategoriaId($descripcion_categoria){
        $sql = "SELECT id FROM categoria WHERE descripcion = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular los parámetros
        $stmt->bind_param("s", $descripcion_categoria);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Obtener el ID de la primera categoría encontrada (debería ser único)
        $categoria = $result->fetch_assoc();
        
        // Cerrar la conexión
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Convertir el array asociativo a formato JSON y devolverlo
        return json_encode($categoria);
    }
    
    
    /**
     * Función que obtiene todas las categorias
     * 
     * 
     * return categoria
     */
    public function getCategorias(){
        $sql = "SELECT * FROM categoria";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Crear un array para almacenar las categorias
        $categorias = array();
        
        // Obtener todas las categorias encontradas
        while ($row = $result->fetch_assoc()) {
            $categorias[] = $row;
        }
        
        // Cerrar la conexión y devolver las categorias como JSON
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($categorias);
    }
    
}

?>