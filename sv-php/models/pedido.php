<?php
require_once 'conexion.php';

class Pedido {

    /**
     * Método que obtiene todos los anuncios comprados
     * 
     * return anuncios
     */
    public function getPedidos($id_usuario) {
        $sql = "SELECT titulo, precio, direccion, ciudad, fotos FROM anuncio JOIN pedido ON pedido.id_anuncio = anuncio.id WHERE pedido.id_comprador = ?";
        
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
     * Función que obtiene todos los pedidos que tiene un usuario por $id_usuario
     * 
     * return -> pedidos
     */
    public function getPedidosIdUsuario($id_usuario){
        $sql = "SELECT * FROM pedido WHERE id_comprador = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_usuario);
    
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Crear un array para almacenar los pedidos
        $pedidos = array();
        
        // Obtener todas los pedidos encontrados
        while ($row = $result->fetch_assoc()) {
            $pedidos[] = $row;
        }
        
        // Cerrar la conexión y devolver los pedidos como JSON
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Verificar si se encontraron mensajes
        if (empty($pedidos)) {
            return json_encode(array('error' => 'No se encontraron pedidos para los parametros proporcionados.'));
        } else {
            return json_encode($pedidos);
        }
    }

    /**
     * Función que inserta un nuevo pedido, comprobando antes
     * si existe en la bd para no insertarlo de nuevo
     * 
     * return -> true / false
     */
    public function insertPedido($id_comprador, $id_anuncio, $direccion, $ciudad, $cp){
        // Comprobar si ya existe el pedido en la bd
        $sql_check = "SELECT COUNT(*) AS total FROM pedido WHERE id_anuncio = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt_check = $conexion->obtenerConexion()->prepare($sql_check);
        
        $stmt_check->bind_param("i", $id_anuncio);
        
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
            return json_encode(false);
        }
        
        // Si no existe, proceder con la inserción del nuevo pedido
        $sql_insert = "INSERT INTO pedido (id_comprador, id_anuncio, direccion, ciudad, cp) VALUES (?, ?, ?, ?, ?)";
        
        $stmt_insert = $conexion->obtenerConexion()->prepare($sql_insert);
        
        $stmt_insert->bind_param("iisss", $id_comprador, $id_anuncio, $direccion, $ciudad, $cp);
        
        $stmt_insert->execute();
        
        // Verificar si la inserción fue exitosa
        $insercion_exitosa = $stmt_insert->affected_rows > 0;
        
        $stmt_insert->close();
        
        $conexion->cerrarConexion();
        
        return json_encode($insercion_exitosa);
    }

    
    public function getAllPedidos() {
        $sql = "SELECT p.id, u.nomb_usu, p.id_anuncio, a.titulo, p.direccion, p.ciudad, p.cp 
        FROM pedido p INNER JOIN anuncio a ON p.id_anuncio = a.id
         INNER JOIN usuario u ON p.id_comprador = u.id;";
        
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
}

?>



