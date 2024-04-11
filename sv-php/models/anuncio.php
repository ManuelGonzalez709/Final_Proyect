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

    /**
     * Método que obtiene todos los datos un anuncio
     *  a partir del $id_anuncio
     * 
     * return anuncio
     */
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

    /**
     * Función que obtiene todos los anuncios que tiene 
     * un usuario creadis a partir del $id_usuario
     * 
     * return anuncios
     */
    public function getAnunciosIdUsuario($id_usuario) {
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
        return $filas_afectadas > 0;
    }

    /**
     * Función que elimina un anuncio existente de un
     * usuario a partir del $id_anuncio
     * 
     * return true / false
     */
    public function deleteAnuncio($id_anuncio) {
        $sql = "DELETE FROM anuncio WHERE id = ?";
        
        $conexion = new Conexion();
        $conexion->conectar();
        
        $stmt = $conexion->obtenerConexion()->prepare($sql);
        
        $stmt->bind_param("i", $id_anuncio);
        
        $stmt->execute();
        
        $filas_afectadas = $stmt->affected_rows;

        $stmt->close();
        $conexion->cerrarConexion();

        return $filas_afectadas > 0;
    }
}

?>