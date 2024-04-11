<?php

class Auth {
    private $conexion;

    public function __construct() {
        $this->conexion = new Conexion();
        $this->conexion->conectar(); // Conecta a la base de datos al crear el objeto
    }
    
    /**
     * Función que verifica la autenticación de un usuario a partir
     * del nombre de usuario y contraseña proporcionada por parámetro.
     * La contraseña proporcioanda se hashea y se comprueba si es la
     * misma que la de la bd hasheada.
     * 
     * return true / false
     */
    public function verificarAutenticacion($nomb_usuario, $contrasena) {
        $sql = "SELECT contras FROM usuario WHERE nomb_usu = ?";

        // Prepara la consulta
        $stmt = $this->conexion->obtenerConexion()->prepare($sql);

        // Vincula el parámetro
        $stmt->bind_param("s", $nomb_usuario);

        $stmt->execute();

        // Obtiene el resultado de la consulta
        $result = $stmt->get_result();

        // Verifica si se encontraron resultados
        if ($result->num_rows == 1) {
            // Obtiene los datos del usuario
            $row = $result->fetch_assoc();
            
            // Obtener la contraseña almacenada en la base de datos
            $contrasena_almacenada = $row['contras'];

            // Verificar si la contraseña coincide utilizando Bcrypt
            if (password_verify($contrasena, $contrasena_almacenada)) {
                // La contraseña coincide, la autenticación es exitosa
                $stmt->close();
                return true;
            } else {
                // La contraseña no coincide, la autenticación falló
                $stmt->close();
                return false;
            }
        } else {
            // No se encontró el usuario, la autenticación falló
            $stmt->close();
            return false;
        }
    }


     /**
      * Función que hashea una contraseña introducida como parámetro en
      * el tipo de hash Bcrypt
      */
     public static function hashContras($password) {
        // Utiliza password_hash para hashear la contraseña
        $hashedPassword = password_hash($password, PASSWORD_DEFAULT);
        return $hashedPassword;
    }
}
?>
