<?php
class ConfigServer {
    public function escribirLog($MetodoEjecutado, $resultadoConsulta) {
        $nombreArchivo = "./util/log/LogServer_".date("mY").".txt";
        $nombreAlmacenDatosGraficos = "./util/log/Data.txt";
        $fecha_actual = date("d/m/Y::H:i:s");
        $ipPeticion = $_SERVER['REMOTE_ADDR'];
        // Contenido a escribir en el archivo
        if(($resultadoConsulta == "null" || $resultadoConsulta == "")&& $resultadoConsulta == FALSE)
        $contenido = $fecha_actual."|".$ipPeticion."|".$MetodoEjecutado."|FAILED";
        else $contenido = $fecha_actual."|".$ipPeticion."|".$MetodoEjecutado."|OK";

        // Intentar abrir el archivo en modo append (si no existe, se creará)
        if ($archivo = fopen($nombreArchivo, "a")) {
            // Escribir el contenido en el archivo
            if (fwrite($archivo, $contenido . PHP_EOL) === false) {
                echo "ServerError: No se pudo escribir en el archivo: " . $nombreArchivo;
            }
            // Cerrar el archivo
            fclose($archivo);
        } else {
            echo "ServerError: No se pudo abrir el archivo: " . $nombreArchivo;
        }
    }
}
?>