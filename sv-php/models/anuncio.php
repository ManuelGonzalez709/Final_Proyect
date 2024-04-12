<?php
require_once 'conexion.php';

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
     * Función que obtiene todos los anuncios que tiene 
     * un usuario creadis a partir del $id_usuario
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
        return $filas_afectadas > 0;
    }

    /**
     * Función que elimina un anuncio existente de un
     * usuario a partir del $id_anuncio
     * 
     * return true / false
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
        } catch(mysqli_sql_exception $exception){
            return json_encode(false); // Retorne false
        } 
    }
}

?>