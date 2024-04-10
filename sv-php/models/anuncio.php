<?php
class Anuncio {
    private $id;
    private $titulo;
    private $descripcion;
    private $estado;
    private $ubicacion;
    private $precio;
    private $divisa;
    private $fotos;
    private $id_categoria;
    private $id_usuario;

    public function __construct($id = null, $titulo = null, $descripcion = null, $estado = null, $ubicacion = null, $precio = null, $divisa = null, $fotos = null, $id_categoria = null, $id_usuario = null) {
        if($id != null && $titulo != null && $descripcion != null && $estado != null && $ubicacion != null && $precio != null && $divisa != null && $fotos != null && $id_categoria != null && $id_usuario != null) {
            $this->id = $id;
            $this->titulo = $titulo;
            $this->descripcion = $descripcion;
            $this->estado = $estado;
            $this->ubicacion = $ubicacion;
            $this->precio = $precio;
            $this->divisa = $divisa;
            $this->fotos = $fotos;
            $this->id_categoria = $id_categoria;
            $this->id_usuario = $id_usuario;
        }
    }
    

    public function getId() { return $this->id; }
    public function getTitulo() { return $this->titulo; }
    public function getDescripcion() { return $this->descripcion; }
    public function getEstado() { return $this->estado; }
    public function getUbicacion() { return $this->ubicacion; }
    public function getPrecio() { return $this->precio; }
    public function getDivisa() { return $this->divisa; }
    public function getIdCategoria() { return $this->id_categoria; }
    public function getFotos() { return $this->fotos; }
    public function getIdUsuario() { return $this->id_usuario; }

    public function setId($id) { $this->id = $id; }
    public function setDescripcion($descripcion) { $this->descripcion = $descripcion; }
    public function setEstado($estado) { $this->estado = $estado; }
    public function setUbicacion($ubicacion) { $this->ubicacion = $ubicacion; }
    public function setPrecio($precio) { $this->precio = $precio; }
    public function setDivisa($divisa) { $this->divisa = $divisa; }
    public function setFotos($fotos) { $this->fotos = $fotos; }
    public function setIdCategoria($id_categoria) { $this->id_categoria = $id_categoria; }
    public function setIdUsuario($id_usuario) { $this->id_usuario = $id_usuario; }

    public function getAnuncioId($id_anuncio){
        $sql = "SELECT * FROM anuncio WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_anuncio);
        
        $stmt->execute();
        
        $result = $stmt->get_result();
        
        $anuncio = null;
        if ($row = $result->fetch_assoc()) {
            $anuncio = new Anuncio(
                $row['id'],
                $row['titulo'],
                $row['descripcion'],
                $row['estado'],
                $row['ubicacion'],
                $row['precio'],
                $row['divisa'],
                $row['fotos'],
                $row['id_categoria'],
                $row['id_usuario'],
            );
        }
    
        $stmt->close();
        $conexion->cerrarConexion();
        return $anuncio;
    }

    public function getAnuncioIdUsuario($id_usuario) {
        $sql = "SELECT * FROM anuncio WHERE id_usuario = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_usuario);
        
        $stmt->execute();
        
        $result = $stmt->get_result();
        
        $anuncios = array();
        
        // Itera sobre los resultados y crea objetos Anuncio para cada uno
        while ($row = $result->fetch_assoc()) {
            $anuncio = new Anuncio(
                $row['id'],
                $row['titulo'],
                $row['descripcion'],
                $row['estado'],
                $row['ubicacion'],
                $row['precio'],
                $row['divisa'],
                $row['fotos'],
                $row['id_categoria'],
                $row['id_usuario']
            );
            $anuncios[] = $anuncio;
        }
    
        $stmt->close();
        $conexion->cerrarConexion();
        return $anuncios;
    }
    
}

?>