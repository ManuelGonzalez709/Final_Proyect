<?php
class Conexion {
    private $host = "127.0.0.1";
    private $usuario = "root";
    private $contrasena = "";
    private $basedatos = "anunciaya";
    private $conexion;

    /**
     * Función que establece conexión con la base de datos MySQL
     */
    public function conectar() {
        $this->conexion = new mysqli($this->host, $this->usuario, $this->contrasena, $this->basedatos);

        // Verifica si hay errores en la conexion
        if ($this->conexion->connect_error) {
            die("Error de conexión: " . $this->conexion->connect_error);
        }

        // Codificacion de caracteres en UTF-8
        $this->conexion->set_charset("utf8");
    }

    /**
     * Función que obtiene la conexión de la bd
     * ya creada en el método conectar()
     */
    public function obtenerConexion() {
        return $this->conexion;
    }

    /**
     * Función que cierra la conexión con 
     * la base de datos
     */
    public function cerrarConexion() {
        if ($this->conexion) {
            $this->conexion->close();
        }
    }
}

?>
