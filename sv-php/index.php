<?php
require_once 'conexion.php';
require_once 'models/direccion_envio.php';
require_once 'controllers/db_manager.php';
require_once 'models/usuario.php';

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

// EJEMPLO AUTENTICACION USUARIO Y CONTRASEÑA

require_once 'util/auth.php';

// Verifica si se ha enviado un formulario de inicio de sesión
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Recupera los datos del formulario
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Crea un objeto Auth
    $auth = new Auth();

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

