<?php
class Anuncio {
    private $id;
    private $id_anuncio;
    private $titulo;
    private $descripcion;
    private $estado;
    private $ubicacion;
    private $precio;
    private $divisa;
    private $fotos;
    private $id_categoria;
    private $id_usuario;

    public function __construct($id, $id_anuncio, $titulo, $descripcion, $estado, $ubicacion, $precio, $divisa, $fotos, $id_categoria, $id_usuario) {
        $this->id = $id;
        $this->id_anuncio = $id_anuncio;
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

    public function getId() { return $this->id; }
    public function getIdAnuncio() { return $this->id_anuncio; }
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
    public function setIdAnuncio($id_anuncio) { $this->id_anuncio = $id_anuncio; }
    public function setTitulo($titulo) { $this->titulo = $titulo; }
    public function setDescripcion($descripcion) { $this->descripcion = $descripcion; }
    public function setEstado($estado) { $this->estado = $estado; }
    public function setUbicacion($ubicacion) { $this->ubicacion = $ubicacion; }
    public function setPrecio($precio) { $this->precio = $precio; }
    public function setDivisa($divisa) { $this->divisa = $divisa; }
    public function setFotos($fotos) { $this->fotos = $fotos; }
    public function setIdCategoria($id_categoria) { $this->id_categoria = $id_categoria; }
    public function setIdUsuario($id_usuario) { $this->id_usuario = $id_usuario; }
}

?>