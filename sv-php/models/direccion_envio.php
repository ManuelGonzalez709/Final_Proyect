<?php
require_once 'conexion.php';
class DireccionEnvio {
    /**
     * Función que obtiene todas las direcciones de envío de un usuario
     * pasado como parámetro $id_usuario
     * 
     * return direcciones_envio
     */
    public function getDirEnvioIdUsuario($id_usuario) {
        $sql = "SELECT * FROM direccion_envio WHERE id_usuario = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular los parámetros
        $stmt->bind_param("i", $id_usuario);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Crear un array para almacenar las direcciones de envío
        $direcciones = array();
        
        // Obtener todas las direcciones de envío encontradas
        while ($row = $result->fetch_assoc()) {
            $direcciones[] = $row;
        }
        
        // Cerrar la conexión y devolver las direcciones de envío como JSON
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($direcciones);
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
        return json_encode($insercion_exitosa);
    }
}

?>
