<?php

include 'models/categoria.php';
include 'models/pedido.php';
include 'models/anuncio.php';
include 'models/usuario.php';

$data = json_decode(file_get_contents('php://input'), true);
if (isset($data['class']) && isset($data['method']) && isset($data['params'])) { 
    // Obtener la clase y el método solicitado
    $class = $data['class'];
    $method = $data['method'];

    // Verificar qué clase se solicita
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
        default:
            echo json_encode(['success' => false, 'error' => 'Clase no encontrada']);
            exit(); // Salir del script si la clase no es válida
    }

    // Verificar si el método solicitado existe en la clase
    if (method_exists($obj, $method)) {
        // Llamar al método correspondiente de la clase
        $result = call_user_func_array([$obj, $method], $data['params']);
        echo json_encode(['success' => true, 'data' => $result]);
    } else {
        echo json_encode(['success' => false, 'error' => 'Metodo no encontrado']);
    }
} else {
    echo json_encode(['success' => false, 'error' => 'No se especifico ningun metodo']);
}


?>