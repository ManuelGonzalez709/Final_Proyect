<?php
class Categoria {
    private $id;
    private $descripcion;

    public function __construct($id, $descripcion){
        $this->id = $id;
        $this->descripcion = $descripcion;
    } 

    public function getId() { return $this->id; }
    public function getDescripcion() { return $this->descripcion; }

    public function setId($id) { $this->id = $id; }
    public function setDescripcion($descripcion) { $this->descripcion = $descripcion; }
}

?>