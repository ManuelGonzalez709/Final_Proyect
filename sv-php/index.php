<?php

include 'models/categoria.php';
include 'models/pedido.php';
include 'models/anuncio.php';
include 'models/usuario.php';
include 'util/ConfigServer.php';

$data = json_decode(file_get_contents('php://input'), true);

if (isset($data['class']) && isset($data['method']) && isset($data['params'])) { 
    // Obtiene la clase y el método solicitado
    $class = $data['class'];
    $method = $data['method'];

    // Verifica que clase se solicita
    switch ($class) {
        case 'Categoria':
            $obj = new Categoria();
            break;
        case 'Pedido':
            $obj = new Pedido();
            break;
        case 'Anuncio':
            $obj = new Anuncio();
            break;
        case 'Usuario':
            $obj = new Usuario();
            break;
        case 'Auth':
            $obj = new Auth();
            break;
        default:
            echo json_encode(['success' => false, 'error' => 'Clase no encontrada']);
            exit(); // Salir del script si la clase no es válida
    }

    // Verifica si el metodo solicitado existe en la clase
    if (method_exists($obj, $method)) {
        // Llamar al metodo correspondiente de la clase
        $result = call_user_func_array([$obj, $method], $data['params']);
        echo json_encode(['success' => true, 'data' => $result]);
        $logWritter = new ConfigServer();
        $logWritter->escribirLog($method, $result);
    } else {
        echo json_encode(['success' => false, 'error' => 'Metodo no encontrado']);
    }
} else {
    echo json_encode(['success' => false, 'error' => 'No se especifico ningun metodo']);
}

?>