<?php
require_once 'conexion.php';

class Categoria {
    /**
     * Función que obtiene todos los datos de una categoria
     * a partir del $id_categoria
     * 
     * return categoria
     */
    public function getCategoriaId($id_categoria){
        $sql = "SELECT * FROM categoria WHERE id = ?";
        
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
        
        // Obtener la primera categoria encontrada (debería ser único)
        $categoria = $result->fetch_assoc();
        
        // Cerrar la conexión y devuelve la categoria
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($categoria);
    }
}

?>