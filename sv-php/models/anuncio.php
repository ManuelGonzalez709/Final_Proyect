<?php
require_once 'conexion.php';

class Anuncio {
    /**
     * Método que obtiene todos los datos un anuncio
     *  a partir del $id_anuncio
     * 
     * return anuncio
     */
    public function getAnuncioId($id_anuncio){
        $sql = "SELECT * FROM anuncio WHERE id = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular los parámetros
        $stmt->bind_param("i", $id_anuncio);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Obtener el primer anuncio encontrado (debería ser único)
        $anuncio = $result->fetch_assoc();
        
        // Cerrar la conexión y devuelve el anuncio
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($anuncio);
    }

    /**
     * Método que obtiene todos los anuncios
     * que existen eliminando los anuncios del
     * usuario pasado como parámetro.
     * Serán los anuncios que se mostrarán en el home.
     * 
     * return anuncio
     */
    public function getAnunciosExcepIdUsuario($id_usuario) {
        $sql = "SELECT * FROM anuncio WHERE id_usuario != ?";
        
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
        
        // Crear un array para almacenar los anuncios
        $anuncios = array();
        
        // Verificar si se encontraron anuncios
        if ($result->num_rows > 0) {
            // Obtener todos los anuncios encontrados
            while ($row = $result->fetch_assoc()) {
                $anuncios[] = $row;
            }
        } else {
            // No se encontraron anuncios, devolver null
            $anuncios = null;
        }
        
        // Cerrar la conexión y devolver los anuncios como JSON
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($anuncios);
    }
    
    /**
     * Función que obtiene todos los anuncios que tiene 
     * un usuario a partir del $id_usuario
     * 
     * return anuncios
     */
    public function getAnunciosIdUsuario($id_usuario) {
        $sql = "SELECT * FROM anuncio WHERE id_usuario = ?";
        
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
        
        // Crear un array para almacenar los anuncios
        $anuncios = array();
        
        // Obtener todos los anuncios encontrados
        while ($row = $result->fetch_assoc()) {
            $anuncios[] = $row;
        }
        
        // Cerrar la conexión y devolver los anuncios como JSON
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($anuncios);
    }

     /**
     * Función que inserta nuevos anuncios de un usuario en específico
     * en la base de datos a partir de los parámetros  $direccion, $cp, 
     * $provincia, $poblacion, $pais, $id_usuario
     * 
     * return -> true / false
     */
    public function insertAnuncio($id_usuario, $id_categoria, $titulo, $descripcion, $estado, $ubicacion, $precio, $divisa, $fotos) {
        // Query SQL para insertar una nuevo anuncio en la base de datos
        $sql = "INSERT INTO anuncio (id_usuario, id_categoria, titulo, descripcion, estado, ubicacion, precio, divisa, fotos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Prepara la sentencia SQL
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Enlaza los parámetros con los valores proporcionados
        $stmt->bind_param("iissssdss", $id_usuario, $id_categoria, $titulo, $descripcion, $estado, $ubicacion, $precio, $divisa, $fotos);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Verifica si la inserción fue exitosa
        $insercion_exitosa = $stmt->affected_rows > 0;
        
        $stmt->close();
        $conexion->cerrarConexion();

        // Retorna true si se insertó correctamente, de lo contrario, retorna false
        return json_encode($insercion_exitosa);
    }

    /**
     * Función que actualiza los datos de un anuncio existente
     *  a partir de  $id_anuncio, $titulo, $descripcion, $estado,
     *  $precio, $divisa, $fotos, $id_categoria
     * 
     * return true / false
     */
    public function updateAnuncio($id_anuncio, $titulo, $descripcion, $estado, $precio, $divisa, $fotos, $id_categoria){
        $sql = "UPDATE anuncio SET titulo = ?, descripcion = ?, estado = ?, precio = ?, divisa = ?, fotos = ?, id_categoria = ? WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("sssdssii", $titulo, $descripcion, $estado, $precio, $divisa, $fotos, $id_categoria, $id_anuncio);
        
        $stmt->execute();
        
        $filas_afectadas = $stmt->affected_rows;
        
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Retorna true si se actualizó al menos una fila, de lo contrario, retorna false
        return json_encode($filas_afectadas > 0);
    }

    /**
     * Función que elimina un anuncio existente de un
     * usuario a partir del $id_anuncio
     * 
     * return true / false / "err_constr"
     */
    public function deleteAnuncio($id_anuncio) {
        try{
            $sql = "DELETE FROM anuncio WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_anuncio);
        
        $stmt->execute();
        
        $filas_afectadas = $stmt->affected_rows;

        $stmt->close();
        $conexion->cerrarConexion();

        return json_encode($filas_afectadas > 0);

        /* Controlamos la excepción por si salta debido a que no se pueden 
           eliminar productos que se han pedido  por el constraint de la bd
         */
    } catch(mysqli_sql_exception $exception) {
        $constraint = "Cannot delete or update a parent row: a foreign key constraint fails";
        
        // Si no se puede eliminar por el constraint retorna un mensaje personalizado
        if(strpos($exception->getMessage(), $constraint) !== false) {
            return json_encode("err_constr"); // Error constraint
        }
    
        return false; // Retorne false
        }
    }    
}

?>