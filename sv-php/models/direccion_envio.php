<?php
class DireccionEnvio {
    private $id;
    private $direccion;
    private $cp;
    private $provincia;
    private $poblacion;
    private $pais;
    private $id_usuario;
    
    public function __construct($id, $direccion, $cp, $provincia, $poblacion, $pais, $id_usuario) {
        $this->id = $id;
        $this->direccion = $direccion;
        $this->cp = $cp;
        $this->provincia = $provincia;
        $this->poblacion = $poblacion;
        $this->pais = $pais;
        $this->id_usuario = $id_usuario;
    }
    
    public function getId() { return $this->id; }
    public function getDireccion() { return $this->direccion; }
    public function getCp() { return $this->cp; }
    public function getProvincia() { return $this->provincia; }
    public function getPoblacion() { return $this->poblacion; }
    public function getPais() { return $this->pais; }

    public function setDireccion($direccion) { $this->direccion = $direccion; }
    public function setCp($cp) { $this->cp = $cp; }
    public function setProvincia($provincia) { $this->provincia = $provincia; }
    public function setPoblacion($poblacion) { return $this->poblacion = $poblacion; }
    public function setPais($pais) { $this->pais = $pais; }

    public static function obtenerDireccionesEnvio($conexion, $id_usuario) {
        $sql = "SELECT * FROM direccion_envio WHERE id_usuario = ?";
        
        // Preparar la consulta
        $stmt = $conexion->prepare($sql);
        
        // Vincular parámetros
        $stmt->bind_param("i", $id_usuario);
        
        $stmt->execute();
        
        // Obtiene los resultados
        $result = $stmt->get_result();
        
        // Array para almacenar las direcciones de envío
        $direcciones = array();
        
        while ($row = $result->fetch_assoc()) {
            $direccion = new DireccionEnvio(
                $row['id'],
                $row['direccion'],
                $row['cp'],
                $row['provincia'],
                $row['poblacion'],
                $row['pais'],
                $row['id_usuario']
            );
            $direcciones[] = $direccion;
        }
        
        $stmt->close();
        
        return $direcciones;
    }
}

?>
