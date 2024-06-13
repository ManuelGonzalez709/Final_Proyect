package es.localhost.anunciaya.administrador.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class ServerComunication {
    public static String comunicacion(String urlServer,String clase, String metodo , String[]params){
        try {
            URL url = new URL(urlServer + "/index.php");

            // Abrir conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String data = "{\"class\":\"" + clase + "\", \"method\":\"" + metodo + "\", \"params\":" + getParamsParsed(params) + "}";

            // Escribir datos en el cuerpo de la solicitud
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            // Leer respuesta del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();
            return response.toString();

        } catch (Exception e) {return null;}
    }
    private static String getParamsParsed(String[] params) {
        String retornador = "[";
        for (int i = 0; i < params.length; i++) {
            if (i != params.length - 1) retornador+="\""+params[i]+"\""+",";
            else retornador+="\""+params[i]+"\""+"]";
        }
        return retornador;
    }
}
