import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // URL del servidor PHP
            URL url = new URL("http://127.0.0.1:84/sv-php/index.php");

            // Abrir conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String className = "Usuario"; // Nombre de la clase
            String method = "getUsuarioId"; // Metodo que deseas llamar
            int id = 1;
            String params = "[" + id + "]"; // Parametros del metodo
            String data = "{\"class\":\"" + className + "\", \"method\":\"" + method + "\", \"params\":" + params + "}";

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

            // Mostrar respuesta del servidor
            System.out.println("Respuesta del servidor:");
            System.out.println(response);

            // Cerrar conexión
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
