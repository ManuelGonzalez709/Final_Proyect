<?php
require_once 'conexion.php';

class Anuncio {
    /**
     * Método que obtiene todos los datos un anuncio
     *  a partir del $id_anuncio
     * 
     * return anuncio
     */

    public function subirFotoBaseDatos($Fotos,$idAnuncio){
        $sql = "UPDATE anuncio SET fotos = ? WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("si",$Fotos,$idAnuncio);
        
        $stmt->execute();
        
        $filas_afectadas = $stmt->affected_rows;
        
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Retorna true si se actualizó al menos una fila, de lo contrario, retorna false
        return json_encode($filas_afectadas > 0);
    }

    /**
     * Método que obtiene todos los anuncios registrados en la aplicación
     * para su visualización por el administrador
     * 
     * return anuncios
     */
    public function getAllAnuncios(){
        $sql = "SELECT a.id, u.nomb_usu, a.titulo, c.descripcion AS categoria, a.descripcion, a.estado, a.ubicacion, 
                a.precio, a.fotos, a.fech_public,
                CASE 
                    WHEN p.id_anuncio IS NOT NULL THEN true
                    ELSE false
                END AS comprado
                FROM anuncio a 
                INNER JOIN usuario u ON a.id_usuario = u.id 
                INNER JOIN categoria c ON a.id_categoria = c.id
                LEFT JOIN pedido p ON a.id = p.id_anuncio;";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);

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
    

    public function getIdNewAnuncioUsuario($id_usuario) {
        $sql = "SELECT MAX(id) AS ultimo_id FROM anuncio WHERE id_usuario = ?";
        
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
        
        // Obtener el primer resultado como un array asociativo
        $anuncio = $result->fetch_assoc();
        
        // Cerrar la conexión y devuelve el resultado
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Retornar el resultado como JSON
        return json_encode($anuncio);
    }

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
    public function getAnunciosExcepIdUsuarioAndPedido($id_usuario) {
        $sql = "SELECT * FROM anuncio WHERE id_usuario != ? AND id NOT IN (SELECT id_anuncio FROM pedido)";
        
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

    public function getAnunciosUbicacion($ubicacion, $id_usuario){
        $sql = "SELECT id, titulo, precio, ubicacion, fotos 
                FROM anuncio WHERE id_usuario != ? AND ubicacion = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);

        $stmt->bind_param("is",$id_usuario, $ubicacion);

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

    public function getAnunciosUbicacionCategoria($ubicacion, $id_categoria, $id_usuario){
        $sql = "SELECT id, titulo, precio, ubicacion, fotos 
                FROM anuncio WHERE id_usuario != ? AND ubicacion = ? AND id_categoria = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);

        $stmt->bind_param("isi",$id_usuario, $ubicacion, $id_categoria);

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

    public function getAnunciosIdCategoria($id_categoria, $id_usuario){
        $sql = "SELECT id, titulo, precio, ubicacion, fotos 
                FROM anuncio WHERE id_usuario != ? AND id_categoria = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);

        $stmt->bind_param("ii",$id_usuario, $id_categoria);

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
     * Método que obtiene todos los productos vendidos
     * 
     * return anuncios
     */
    public function getEnvios($id_usuario) {
        $sql = "SELECT anuncio.* FROM usuario JOIN anuncio ON anuncio.id_usuario = usuario.id JOIN pedido ON pedido.id_anuncio = anuncio.id WHERE usuario.id = ?;";
        
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
        $sql = "SELECT * FROM anuncio WHERE id_usuario = ? AND id NOT IN (SELECT id_anuncio FROM pedido)";
        
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
    public function insertAnuncio($id_usuario, $id_categoria, $titulo, $descripcion, $estado, $ubicacion, $precio, $fotos) {
        // Query SQL para insertar un nuevo anuncio en la base de datos
        $sql = "INSERT INTO anuncio (id_usuario, id_categoria, titulo, descripcion, estado, ubicacion, precio, fotos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Obtiene la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Prepara la sentencia SQL
        $stmt = $conexion->obtenerConexion()->prepare($sql);
    
        // Enlaza los parámetros con los valores proporcionados
        $stmt->bind_param("iissssds", $id_usuario, $id_categoria, $titulo, $descripcion, $estado, $ubicacion, $precio, $fotos);
        
        // Ejecuta la consulta
        $stmt->execute();
    
        // Verifica si la inserción fue exitosa
        $insercion_exitosa = $stmt->affected_rows > 0;
    
        // Cierra la sentencia y la conexión
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
    // ["18", "Anuncio modific...", "Descripción mod...", "Nuevo", "Belalcázar", +3 more]
    public function updateAnuncio($id_anuncio, $titulo, $descripcion, $estado, $ubicacion,$precio, $fotos, $id_categoria){
        $sql = "UPDATE anuncio SET titulo = ?, descripcion = ?, estado = ?,ubicacion = ?, precio = ?, fotos = ?, id_categoria = ? WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("ssssdsii", $titulo, $descripcion, $estado,$ubicacion, $precio, $fotos, $id_categoria, $id_anuncio);
        
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
    $conexion = new Conexion();
    $conexion->conectar();

    // Verificar si el anuncio está en la tabla pedido
    $sqlCheck = "SELECT COUNT(*) as count FROM pedido WHERE id_anuncio = ?";
    $stmtCheck = $conexion->obtenerConexion()->prepare($sqlCheck);
    $stmtCheck->bind_param("i", $id_anuncio);
    $stmtCheck->execute();
    $resultCheck = $stmtCheck->get_result();
    $rowCheck = $resultCheck->fetch_assoc();
    $stmtCheck->close();

    if ($rowCheck['count'] > 0) {
        // Si el anuncio está en la tabla pedido, retornar false
        $conexion->cerrarConexion();
        return json_encode(false);
    }

    // Eliminar el anuncio de la tabla anuncio
    $sqlDelete = "DELETE FROM anuncio WHERE id = ?";
    $stmtDelete = $conexion->obtenerConexion()->prepare($sqlDelete);
    $stmtDelete->bind_param("i", $id_anuncio);
    $stmtDelete->execute();
    
    $filas_afectadas = $stmtDelete->affected_rows;

    $stmtDelete->close();
    $conexion->cerrarConexion();

    return json_encode($filas_afectadas > 0);
}
   
}

?>