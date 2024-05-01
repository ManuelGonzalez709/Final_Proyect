<?php
require_once 'conexion.php';
require_once 'util/auth.php';

class Usuario {
<<<<<<< HEAD
=======
    
>>>>>>> 4da6a5a8611f7ae8fcb35f6896c420c5b5ca3fa3
    /**
     * Función que obtiene todos los datos de un usuario insertado
     * en una base de datos a partir del $id_usuario
     * 
     * return usuario
     */
    public function getUsuarioDataId($id_usuario) {
        $sql = "SELECT * FROM usuario WHERE id = ?";
        
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
        
        // Obtener el primer usuario encontrado (debería ser único)
        $usuario = $result->fetch_assoc();
        
        // Cerrar la conexión y devuelve el usuario
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($usuario);
    }

    /**
     * Función que crea un nuevo usuario mediante los datos proporcionados
     * como parámetro.
     * 
     * return true / false
     */
    public function insertUsuario($nombre, $apellidos, $nomb_usu, $contras, $fecha_nacimiento, $email, $telefono){
        $contrasena_cifrada = Auth::hashContras($contras);

        $sql = "INSERT INTO usuario (nombre, apellidos, nomb_usu, contras, fecha_nacimiento, email, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("sssssss", $nombre, $apellidos, $nomb_usu, $contrasena_cifrada, $fecha_nacimiento, $email, $telefono);
        
        $stmt->execute();
        
        // Verifica si la inserción fue exitosa
        $insercion_exitosa = $stmt->affected_rows > 0;
        
        $stmt->close();
        $conexion->cerrarConexion();

        // Retorna true si se insertó correctamente, de lo contrario, retorna false
        return json_encode($insercion_exitosa);
    }

    /**
     * Función que actualiza los datos de un usuario existente en la base de datos
     * a partir de los parámetros $id_usuario, $nombre, $apellidos, $telefono, $email,
     *  $fecha_nac, $nomb_usu
     * 
     * return true / false
     */
    public function updateUsuario($id_usuario, $nombre, $apellidos, $telefono, $email, $fecha_nac, $nomb_usu){
        try{
            $sql = "UPDATE usuario SET nombre = ?, apellidos = ?, telefono = ?, email = ?, fecha_nacimiento = ?, nomb_usu = ? WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("ssssssi", $nombre, $apellidos, $telefono, $email, $fecha_nac, $nomb_usu, $id_usuario);
        
        $stmt->execute();
        
        // Verifica si la actualización fue exitosa
        $filas_afectadas = $stmt->affected_rows;
        
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Retorna true si se actualizó al menos una fila, de lo contrario, retorna false
        return json_encode($filas_afectadas > 0);
        } catch(mysqli_sql_exception $exception){
            echo $exception;
        
            // Definir las cadenas que identifican las violaciones de restricciones únicas
            $unique_nomb_usu = "Duplicate entry 'usuario777' for key 'UNIKE_nomb_usu'";
            $unique_email = "Duplicate entry 'nuevo@correo.com' for key 'UNIKE_email'";
            
            // Arreglo para almacenar los mensajes de error
            $error_message = "excepcion";
        
            // Si la excepción indica una violación de la restricción de nombre de usuario único, añadir al arreglo de mensajes de error
            if(strpos($exception->getMessage(), $unique_nomb_usu) !== false) {
                $error_message[0] = "unique_nomb_usu"; // Error constraint
            } 
        
            // Si la excepción indica una violación de la restricción de correo electrónico único, añadir al arreglo de mensajes de error
            if(strpos($exception->getMessage(), $unique_email) !== false) {
                $error_message[1] = "unique_email"; // Error constraint
            }
        
            return json_encode(["error" => $error_message]);
        }  
    }

    /**
     * Función que actualiza la contraseña de un usuario existente en una base de 
     * datos a partir de una nueva contraseña hasheada proporcionada como parámetro
     * $nueva_contrasena
     * 
     * return true / false
     */
    public function updateContras($id_usuario, $nueva_contrasena) {
        // Hashear la nueva contraseña antes de actualizarla en la base de datos
        $contrasena_hasheada = Auth::hashContras($nueva_contrasena);
    
        $sql = "UPDATE usuario SET contras = ? WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Enlaza los parámetros con los valores proporcionados
        $stmt->bind_param("si", $contrasena_hasheada, $id_usuario);
        
        // Ejecuta la consulta
        $stmt->execute();
        
        // Verifica si la actualización fue exitosa
        $filas_afectadas = $stmt->affected_rows;
        
        // Cierra la conexión y el statement
        $stmt->close();
        $conexion->cerrarConexion();
        
        // Retorna true si se actualizó al menos una fila, de lo contrario, retorna false
        return json_encode($filas_afectadas > 0);
    }

    public function getIdUser($nombre_usuario){
        $sql = "SELECT id FROM usuario WHERE nomb_usu = ?";
        
        // Obtener la conexión a la base de datos
        $conexion = new Conexion();
        $conexion->conectar();
        
        // Preparar la sentencia
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        // Vincular los parámetros
        $stmt->bind_param("s", $nombre_usuario);
        
        // Ejecutar la consulta
        $stmt->execute();
        
        // Obtener el resultado de la consulta
        $result = $stmt->get_result();
        
        // Obtener el primer usuario encontrado (debería ser único)
        $usuario = $result->fetch_assoc();
        
        // Cerrar la conexión y devuelve el usuario
        $stmt->close();
        $conexion->cerrarConexion();
        return json_encode($usuario);
    }


}

?>