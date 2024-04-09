<?php
class Pedido {
    private $id;
    private $id_anuncio;
    private $id_usuario;
    private $fecha;

    public function __construct($id, $id_anuncio, $id_usuario, $fecha) {
        $this->id = $id;
        $this->id_anuncio = $id_anuncio;
        $this->id_usuario = $id_usuario;
        $this->fecha = $fecha;
    }

    public function getId() { return $this->id; }
    public function getIdAnuncio() { return $this->id_anuncio; }
    public function getIdUsuario() { return $this->id_usuario; }
    public function getFecha() { return $this->fecha; }

    public function setId($id) { $this->id = $id; }
    public function setIdAnuncio($id_anuncio) { $this->id_anuncio = $id_anuncio; }
    public function setIdUsuario($id_usuario) { $this->id_usuario = $id_usuario; }
    public function setFecha($fecha) { $this->fecha = $fecha; }
}

?>



