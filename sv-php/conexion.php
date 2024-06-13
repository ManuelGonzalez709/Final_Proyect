<?php

class Conexion {
    private $host = "PMYSQL175.dns-servicio.com:3306";
    private $usuario = "sv-anunciaya";
    private $contrasena = "S7^4xb9i3";
    private $basedatos = "10454915_anunciaya";
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
