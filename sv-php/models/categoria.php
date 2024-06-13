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

    public function insertCategoria($descripcion){
        $sql = "INSERT INTO categoria (descripcion) VALUES (?)";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("s", $descripcion);
        
        $stmt->execute();
        
        // Verifica si la inserción fue exitosa
        $insercion_exitosa = $stmt->affected_rows > 0;
        
        $stmt->close();
        $conexion->cerrarConexion();

        // Retorna true si se insertó correctamente, de lo contrario, retorna false
        return json_encode($insercion_exitosa);
    }

    public function tieneAnunciosCategoria($id_categoria){
        $sql = "SELECT COUNT(*) AS count FROM anuncio WHERE id_categoria = ?;";
    
        // Crear una instancia de la clase Conexion
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la consulta
        $stmt = $conexion->obtenerConexion()->prepare($sql);

        // Enlazar el parámetro
        $stmt->bind_param("i", $id_categoria);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado
        $result = $stmt->get_result();
        
        // Obtener la fila del resultado
        $row = $result->fetch_assoc();
        
        // Verificar si hay más de 0 anuncios con el id_categoria
        $hay_anuncios = $row['count'] > 0;
        
        // Cerrar el statement y la conexión
        $stmt->close();
        $conexion->cerrarConexion();
    
        // Retornar true si hay más de 0 anuncios con el id_categoria, y false si no hay ninguno
        return json_encode($hay_anuncios);
    }
    

    public function updateCategoria($id_categoria, $descripcion){
        $sql = "UPDATE categoria SET descripcion = ? WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Cambiar "is" a "si" para vincular el primer parámetro como string y el segundo como entero
        $stmt->bind_param("si", $descripcion, $id_categoria);
    
        $stmt->execute();
        
        // Verifica si la actualización fue exitosa
        $filas_afectadas = $stmt->affected_rows;
        
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Retorna true si se actualizó al menos una fila, de lo contrario, retorna false
        return json_encode($filas_afectadas > 0);
    }

    public function deleteCategoria($id_categoria) {
        $sql = "DELETE FROM categoria WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_categoria);
        
        $stmt->execute();
        
        // Verificar si la eliminación fue exitosa
        $filas_afectadas = $stmt->affected_rows;
    
        $stmt->close();
        $conexion->cerrarConexion();
    
        // Retorna true si se eliminó al menos una fila, de lo contrario, retorna false
        return json_encode($filas_afectadas > 0);
    }
        
    
}

?>