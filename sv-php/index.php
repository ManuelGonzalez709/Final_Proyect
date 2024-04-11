<?php
require_once ('conexion.php');
require_once ('models/usuario.php');
require_once ('util/auth.php');
require_once ('models/mensaje.php');
require_once ('models/usuario.php');
require_once ('models/direccion_envio.php');
require_once ('models/categoria.php');
require_once ('models/anuncio.php');
require_once ('models/pedido.php');
/* EJEMPLO OBTENER DIRECCIONES ENVIO POR ID
$controlador = new DBManager();

// Establecer la conexión a la base de datos
$conexion->conectar();


$id_usuario = 1;

// Llamar al método obtenerDireccionesEnvio de la clase DireccionEnvio
$direcciones = DireccionEnvio::obtenerDireccionesEnvio($conexion->obtenerConexion(), $id_usuario);

// Iterar sobre las direcciones obtenidas y mostrarlas
foreach ($direcciones as $direccion) {
    echo "Id: " . $direccion->getId() . "<br>";
    echo "Dirección: " . $direccion->getDireccion() . "<br>";
    echo "CP: " . $direccion->getCp() . "<br>";
    echo "Provincia: " . $direccion->getProvincia() . "<br>";
    echo "Población: " . $direccion->getPoblacion() . "<br>";
    echo "País: " . $direccion->getPais() . "<br>";
    echo "<br>";
}

$conexion->cerrarConexion();ç
*/

// EJEMPLO OBTENER NOMB_USUARIO Y CONTRAS POR ID USUARIO
/* $dbManager = new DBManager();

// Supongamos que el ID del usuario que quieres consultar es 1
$id_usuario = 1;

// Llamar al método obtNombUsuyContras para obtener el nombre de usuario y contraseña
$usuario = $dbManager->obtNombUsuyContras($id_usuario);

// Verificar si se encontró el usuario
if ($usuario) {
    // Obtener el nombre de usuario y la contraseña
    $nombre_usuario = $usuario->getNombUsu();
    $contrasena = $usuario->getContras();

    // Utilizar los datos obtenidos
    echo "Nombre de Usuario: " . $nombre_usuario . "<br>";
    echo "Contraseña: " . $contrasena;
} else {
    // Si no se encontró el usuario
    echo "No se encontró ningún usuario con el ID proporcionado.";
}*/


// Devuelve todos los datos del usuario 1
$usuario = new Usuario();
$usuarios = $usuario->getUsuarioId(1);

foreach ($usuarios as $user) {
    echo $user . "<br>";
}

// Devuelve todas las direcciones de envio con el id usuario
$dir_env = new DireccionEnvio();
$dirs = $dir_env->getDirEnvioIdUsuario(1);

foreach($dirs as $d){
    echo "ID: " . $d->getId() . ", Dirección: " . $d->getDireccion() . ", CP: " . $d->getCp() . ", Provincia: " . $d->getProvincia() . ", Población: " . $d->getPoblacion() . ", País: " . $d->getPais() . ", ID Usuario: " . $d->getIdUsuario() . "<br>";
}

// Devuelve la categoria por id introducido
$cat = new Categoria();
$categoria = $cat->getCategoriaId(3);
echo "ID: " . $categoria->getId() . ", Descripcion: " . $categoria->getDescripcion() . "<br>";

// Devuelve los anuncios por id_anuncio introducido
$anun = new Anuncio();
$anuncio = $anun->getAnuncioId(1);
echo "<br>Todos los datos de un anuncio mediante el id_anuncio<br>";
echo "<hr>";
echo "ID: " . $anuncio->getId() . ", Titulo: " . $anuncio->getTitulo() . ", Descripcion: " . $anuncio->getDescripcion() . "<br>";

// Devuelve los anuncios por id_usuario introducido
$anun1 = new Anuncio();
$anuncio1 = $anun1->getAnunciosIdUsuario(1);
echo "<br>Todos los anuncios que tiene el usuario con id_usuario 1<br>";
echo "<hr>";
foreach($anuncio1 as $a){
    echo "ID: " . $a->getId() . ", Titulo: " . $a->getTitulo() . ", Descripcion: " . $a->getDescripcion() . "<br>";
}
echo "<br>";

// Devuelve los pedidos por id_pedido
/*
$ped = new Pedido();
$pedido = $ped->getPedidoId(1);

echo "Pedido por id_pedido";
echo "<hr>";
echo "ID: " . $pedido->getId() . ", ID_Comprador: " . $pedido->getIdComprador() . ", ID_Anuncio: " . $pedido->getIdAnuncio() . ", Fecha: " . $pedido->getFecha() . "<br>";
echo "<br>";*/


// Devuelve todos los pedidos de un usuario comprador por id
$ped1 = new Pedido();
$pedidosUsuario1 = $ped1->getPedidosIdUsuario(3);

echo "Todos los pedidos que tiene el usuario con id_usuario 3<br>";
echo "<hr>";

if (!empty($pedidosUsuario1)) {
    foreach ($pedidosUsuario1 as $pedido) {
        echo "ID: " . $pedido->getId() . ", ID_Comprador: " . $pedido->getIdComprador() . ", ID_Articulo: " . $pedido->getIdAnuncio() . ", Fecha: " . $pedido->getFecha() . "<br>";
    }
} else {
    echo "El usuario no tiene pedidos.";
}
echo "<br>";


// Devuelve todos los mensajes por id anuncio, id_emisor e id_receptor
echo "Todos los mensajes por id anuncio, id_emisor e id_receptor <br>";
echo "<hr>";
$mensj = new Mensaje();
$mensajes = $mensj->getMensajesId(1,3,1);

foreach($mensajes as $m){
    echo "Mensaje: " . $m->getMensaje() . ", Fecha/Hora: " . $m->getFecha() . "<br>";
}

// Método que actualiza un usuario existente
// --------------------------------------------
/*
$id = 3;
$nombre = "Nombre actualizado";
$apellidos = "Apellidos actualizados";
$telefono = "989000031";
$email = "nuevo@correo.com";
$fecha_nac = "1990-01-01";
$nomb_usu = "nuevousuario";

$usuario = new Usuario();
$actualizacion_exitosa = $usuario->updateUsuario($id, $nombre, $apellidos, $telefono, $email, $fecha_nac, $nomb_usu);
echo "<br>";
if ($actualizacion_exitosa) {
    echo "Usuario actualizado correctamente.";
} else {
    echo "Hubo un error al actualizar el usuario.";
}*/

// Método que actualiza a la nueva contraseña de un usuario
//----------------------------------------------------------
/*$usuario = new Usuario();
$actualizacion_ok = $usuario->updateContras(1, '1234');
if($actualizacion_ok){
    echo "Contraseña actualizada!<br>";
} else{
    echo "Hubo un error al actualizar la contraseña...<br>";
}*/


// Método que añade una nueva direccion de envio
// ---------------------------------------------
/*echo "<br>";
$dir = new DireccionEnvio();
$nueva_dir = $dir->insertDirEnc("Calle de ejemplo 12", "12092", "Málaga", "La Malagueta", "España", 1);
if($nueva_dir){
    echo "Direccion de envio añadida!<br>";
} else{
    echo "Hubo un error al añadir una direccion de envio..<br>";
}*/

// Método que actualiza un anuncio existente
// --------------------------------------------
/*
echo "<br>";
$anun = new Anuncio();
$anuncio = $anun->updateAnuncio(2, "Anuncio Actualizado", "Descripcion del anuncio actualizado", "Nuevo", 12.34, "Euro", "url_rutas_imagenes", 1);
if($anuncio){
    echo "El anuncio se actualizo correctamente!<br>";
} else{
    echo "Error, no se pudo actualizar el anuncio correctamente...<br>";
}*/


// Método que elimina un anuncio por id_anuncio
/* --------------------------------------------
echo "<br>";
$anunc = new Anuncio();
$anuncio = $anunc->deleteAnuncio(2);
if($anuncio){
    echo "El anuncio se eliminó correctamente!<br>";
} else{
    echo "Error, no se pudo eliminar el anuncio correctamente...<br>";
}*/


// Método que inserta un nuevo mensaje
/* ------------------------------------
echo "<br>";
$mnsj = new Mensaje();
$mensaje = $mnsj->insertMensaje(1,3,"Mensaje de prueba a traves de codigo php.", 1);
if($mensaje){
    echo "El mensaje se insertó correctamente!<br>";
} else{
    echo "Error, no se pudo insertar el mensaje correctamente...<br>";
}*/


// Método que inserta un nuevo pedido
echo "<br>";
$p = new Pedido();
$pedido = $p->insertPedido(2,4);
if($pedido){
    echo "El pedido se insertó correctamente!<br>";
} else{
    echo "Error, no se pudo insertar el pedido correctamente...<br>";
}

// Verifica si se ha enviado un formulario de inicio de sesión
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Recupera los datos del formulario
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Crea un objeto Auth
    $auth = new Auth();
    $pass = $auth->hashContras("usuario");
    echo $pass;
    echo "<br>";

    // Verifica la autenticación
    if ($auth->verificarAutenticacion($username, $password)) {
        // Autenticación exitosa
        echo "Inicio de sesión exitoso";
    } else {
        // Autenticación fallida
        echo "Nombre de usuario o contraseña incorrectos";
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
        <label for="username">Nombre de Usuario:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Contraseña:</label><br>
        <input type="password" id="password" name="password"><br><br>
        <input type="submit" value="Iniciar Sesión">
    </form>
</body>
</html>

