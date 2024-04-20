package com.example.anunciaya.tools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerComunication {
    public ServerComunication(){}

    public static String comunicacion(String UrlServer , String clase, String metodo , String[]params){
        try {
            // URL del servidor PHP
            URL url = new URL(UrlServer);

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

        } catch (Exception e) {
            Log.e("ErrorServer", "Error al comunicarse con el servidor", e);
            return null;
        }
    }
    private static String getParamsParsed(String[] params) {
        String retornador = "[";
        for (int i = 0; i < params.length; i++) {
            if (i != params.length - 1) {
                retornador+="\""+params[i]+"\""+",";
            } else {
                retornador+="\""+params[i]+"\""+"]";
            }
        }
        return retornador;
    }
}
