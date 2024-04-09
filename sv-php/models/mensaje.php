<?php
class Mensaje {
    private $id;
    private $id_chat;
    private $id_comprador;
    private $id_vendedor;
    private $mensaje;
    private $fecha;
    private $id_anuncio;

    public function __construct($id, $id_chat, $id_comprador, $id_vendedor, $mensaje, $fecha, $id_anuncio) {
        $this->id = $id;
        $this->id_chat = $id_chat;
        $this->id_comprador = $id_comprador;
        $this->id_vendedor = $id_vendedor;
        $this->mensaje = $mensaje;
        $this->fecha = $fecha;
        $this->id_anuncio = $id_anuncio;
    }

    public function getId() { return $this->id; }
    public function getIdChat() { return $this->id_chat; }
    public function getIdComprador() { return $this->id_comprador; }
    public function getIdVendedor() { return $this->id_vendedor; }
    public function getMensaje() { return $this->mensaje; }
    public function getFecha() { return $this->fecha; }
    public function getIdAnuncio() { return $this->id_anuncio; }

    public function setId($id) { $this->id = $id; }
    public function setIdChat($id_chat) { $this->id_chat = $id_chat; }
    public function setIdComprador($id_comprador) { $this->id_comprador = $id_comprador; }
    public function setIdVendedor($id_vendedor) { $this->id_vendedor = $id_vendedor; }
    public function setMensaje($mensaje) { $this->mensaje = $mensaje; }
    public function setFecha($fecha) { $this->fecha = $fecha; }
    public function setIdAnuncio($id_anuncio) { $this->id_anuncio = $id_anuncio; }
}

?>