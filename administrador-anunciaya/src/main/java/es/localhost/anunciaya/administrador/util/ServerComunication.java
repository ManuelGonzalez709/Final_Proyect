package es.localhost.anunciaya.administrador.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Clase para gestionar la comunicación con el servidor mediante peticiones HTTP POST.
 *
 * @author AnunciaYa
 */
public class ServerComunication {

    /**
     * Realiza una comunicación con el servidor utilizando HTTP POST.
     *
     * @param urlServer URL del servidor al que se realizará la comunicación.
     * @param clase Nombre de la clase en el servidor a invocar.
     * @param metodo Nombre del método de la clase a invocar.
     * @param params Arreglo de parámetros para enviar al servidor.
     * @return La respuesta del servidor como una cadena de texto, o null si ocurre un error.
     */
    public static String comunicacion(String urlServer, String clase, String metodo, String[] params) {
        try {
            // Construir la URL completa para la solicitud POST
            URL url = new URL(urlServer + "/index.php");

            // Abrir conexión HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Construir el cuerpo de la solicitud en formato JSON
            String data = "{\"class\":\"" + clase + "\", \"method\":\"" + metodo + "\", \"params\":" + getParamsParsed(params) + "}";

            // Escribir datos en el cuerpo de la solicitud
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            // Leer respuesta del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            // Cerrar recursos y desconectar la conexión
            conn.disconnect();
            return response.toString();

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formatea los parámetros en formato JSON para enviar al servidor.
     *
     * @param params Arreglo de parámetros a formatear.
     * @return Una cadena JSON formateada de los parámetros.
     */
    private static String getParamsParsed(String[] params) {
        String retornador = "[";
        for (int i = 0; i < params.length; i++) {
            if (i != params.length - 1)
                retornador += "\"" + params[i] + "\"" + ",";
            else
                retornador += "\"" + params[i] + "\"" + "]";
        }
        return retornador;
    }
}
