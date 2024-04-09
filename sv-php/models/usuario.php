<?php
class Usuario {
    private $id;
    private $nombre;
    private $apellidos;
    private $telefono;
    private $email;
    private $fecha_nac;
    private $nomb_usu;
    private $contras;

    public function __construct($id, $nombre, $apellidos, $telefono, $email, $fecha_nac, $nomb_usu, $contras) {
        $this->id = $id;
        $this->nombre = $nombre;
        $this->apellidos = $apellidos;
        $this->telefono = $telefono;
        $this->email = $email;
        $this->fecha_nac = $fecha_nac;
        $this->nomb_usu = $nomb_usu;
        $this->contras = $contras;
    }

    public function getId() { return $this->id; }
    public function getNombre() { return $this->nombre; }
    public function getApellidos() { return $this->apellidos; }
    public function getTelefono() { return $this->telefono; }
    public function getEmail() { return $this->email; }
    public function getFechaNac() { return $this->fecha_nac; }
    public function getNombUsu() { return $this->nomb_usu; }
    public function getContras() { return $this->contras; }

    public function setNombre($nombre) { $this->nombre = $nombre; }
    public function setApellidos($apellidos) { $this->apellidos = $apellidos; }
    public function setTelefono($telefono) { $this->telefono = $telefono; }
    public function setEmail($email) { $this->email = $email; }
    public function setFechaNac($fecha_nac) { $this->fecha_nac = $fecha_nac; }
    public function setNombUsu($nomb_usu) { $this->nomb_usu = $nomb_usu; }
    public function setContras($contras) { $this->contras = $contras; }
    
}

?>