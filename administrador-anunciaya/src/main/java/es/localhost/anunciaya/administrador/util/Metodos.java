package es.localhost.anunciaya.administrador.util;

import es.localhost.anunciaya.administrador.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que contiene métodos útiles para la gestión y comunicación con el servidor.
 *
 * @author AnunciaYa
 */
public class Metodos {
    private int IdUser = 0;
    /**
     * URL del servidor php.
     */
    private String urlServer = "https://40945016.servicio-online.net/sv-php/";

    public Metodos(int id){IdUser = id;}
    public Metodos(){}


    /**
     * Método que actualiza la contraseña de un usuario en la BD.
     * @param params parámetros del método.
     * @return true / false.
     */
    public Boolean updateContras(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","updateContras",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Actualizacion Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }

    /**
     * Método que actualiza los datos de un usuario en la BD.
     * @param params parámetros del método.
     * @return true / false.
     */
    public Boolean actualizarUsuario(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","updateUsuario",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Actualizacion Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }

    /**
     * Método que elimina un usuario de la BD.
     * @param params parámetros del método.
     * @return true / false.
     */
    public Boolean borrarUsuario(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","borrarUsuario",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }

    /**
     * Método que inserta un nuevo usuario
     * @param params parámetros del método
     * @return true / false
     */
    public Boolean insertarUsuario(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","AdminInsertUsuario",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }

    /**
     * Método que obtiene el id de un usuario a partir del email
     * @param email email usuario.
     * @return string id usuario.
     */
    public String getIdUserByEmail(String email){
        try{
            String []params = {email};
            String respuesta = ServerComunication.comunicacion(urlServer,"Usuario","getIdUserByEmail",params);
            JSONObject jsonObject = new JSONObject(respuesta);
            JSONObject jsonObject2 = new JSONObject(jsonObject.getString("data"));
            return Integer.toString(jsonObject2.getInt("id"));
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método de autenticación del usuario administrador
     * @param params parámetros del método
     * @return true / false
     */
    public Boolean AuthAdmin(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Auth","verificarAuthAdmin",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e){
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.",
                    "Ocurrio un error en el servidor: "+e,
                    Alert.AlertType.INFORMATION);
            return false;
        }
    }

    /**
     * Método que obtiene todos los usuarios de la base de datos excepto el usuario logeado.
     * @param args parámetros del método.
     * @return ObservableList<User>
     */
    public ObservableList<User> getAllUsers(String[] args) {
        try{
            String respuestaServer = ServerComunication.comunicacion(urlServer,"Usuario","getUsers",args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(respuestaServer);
            String dataString = jsonNode.get("data").asText();
            JsonNode dataNode = objectMapper.readTree(dataString);
            ObservableList<User>listaUsuarios =  FXCollections.observableArrayList();
            for (JsonNode node : dataNode) {
                int id = node.get("id").asInt();
                String nombre = node.get("nombre").asText();
                String apellidos = node.get("apellidos").asText();
                String nomb_usu = node.get("nomb_usu").asText();
                String fecha_nacimiento = node.get("fecha_nacimiento").asText();
                String email = node.get("email").asText();
                String telefono = node.get("telefono").asText();
                String tipo = node.get("tipo").asText();
                User usuario = new User(id, nombre, apellidos, nomb_usu, fecha_nacimiento, email, telefono, tipo);
                listaUsuarios.add(usuario);
            }

            return listaUsuarios;

        } catch (Exception e) {
            // Manejar la excepción de procesamiento JSON
            Util.mostrarDialogo("Server_Error", "Error con el servidor","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método que obtiene y muestra el uso de datos por dirección IP a partir de un archivo de registro.
     *
     * @param fichero El nombre del archivo de registro del cual se obtendrá la información.
     * @return Una lista observable de datos para mostrar en un gráfico de tarta (PieChart).
     *         Cada dato representa una dirección IP y la cantidad de peticiones asociadas a ella.
     */
    public ObservableList<PieChart.Data> usoDatosIp(String fichero) {
        try {
            ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();
            URL url = new URL(urlServer + "util/log/" + fichero);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lector;
            String contenidoLog = "";
            while ((lector = reader.readLine()) != null) {
                contenidoLog += lector + "\n";
            }
            ArrayList<String> ips = getAllIp(contenidoLog);
            for (int i = 0; i < ips.size(); i++) {
                piechartData.add(new PieChart.Data(ips.get(i), contarPeticiones(contenidoLog, ips.get(i))));
            }

            return piechartData;
        } catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Ha ocurrido un error.", "Ocurrió un error en el servidor: " + e, Alert.AlertType.INFORMATION);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que obtiene y muestra el estado de las peticiones (OK/FAILED) a partir de un archivo de registro.
     *
     * @param fichero El nombre del archivo de registro del cual se obtendrá la información.
     * @return Una lista observable de datos para mostrar en un gráfico de tarta (PieChart).
     *         Cada dato representa el estado (OK o FAILED) y la cantidad de peticiones asociadas a ese estado.
     */
    public ObservableList<PieChart.Data> obtenerEstadosPeticiones(String fichero) {
        try {
            ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();
            URL url = new URL(urlServer + "util/log/" + fichero);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lector;
            String contenidoLog = "";
            while ((lector = reader.readLine()) != null) {
                contenidoLog += lector + "\n";
            }
            piechartData.add(new PieChart.Data("OK", contarPeticiones(contenidoLog, "OK")));
            piechartData.add(new PieChart.Data("FAILED", contarPeticiones(contenidoLog, "FAILED")));
            reader.close();
            return piechartData;
        } catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.", "Ocurrió un error en el servidor: " + e, Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método que obtiene todos los anuncios desde el servidor y los convierte en una lista observable.
     *
     * @param args Los argumentos adicionales que pueden ser necesarios para la obtención de los anuncios.
     * @return Una lista observable de objetos Anuncio que contiene todos los anuncios recuperados desde el servidor.
     */
    public ObservableList<Anuncio> getAllAnuncios(String[] args) {
        try {
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Anuncio", "getAllAnuncios", args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(respuestaServer);
            JsonNode dataNode = objectMapper.readTree(jsonNode.get("data").asText());

            ObservableList<Anuncio> listaAnuncios = FXCollections.observableArrayList();

            for (JsonNode node : dataNode) {
                int id = node.get("id").asInt();
                String nombUsu = node.get("nomb_usu").asText();
                String titulo = node.get("titulo").asText();
                String categoria = node.get("categoria").asText();
                String descripcion = node.get("descripcion").asText();
                String estado = node.get("estado").asText();
                String ubicacion = node.get("ubicacion").asText();
                double precio = node.get("precio").asDouble();
                String fotos = node.get("fotos").asText();
                String fechPublic = node.get("fech_public").asText();
                int isComprado = node.get("comprado").asInt();

                Anuncio a = new Anuncio(id, nombUsu, categoria, titulo, descripcion, estado, ubicacion,
                        precio, fotos, fechPublic, isComprado == 1 ? "Sí" : "No");
                listaAnuncios.add(a);
            }

            return listaAnuncios;

        } catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Error con el servidor", "Ocurrió un error en el servidor: " + e, Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método que elimina un anuncio de la BD.
     * @param args parámetros del método.
     * @return true / false.
     */
    public boolean deleteAnuncio(String[] args) {
        try {
            // Lanza la petición a tu servicio web
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Anuncio", "deleteAnuncio", args);

            // Obtén la respuesta del servidor
            JSONObject jsonObject = new JSONObject(respuestaServer);

            // Devuelve la respuesta de data como booleano
            if (jsonObject.has("data")) {
                return jsonObject.getBoolean("data");
            } else {
                return false;
            }
        } catch (Exception e) {
            // Si hay alguna excepción, devuelve false
            return false;
        }
    }

    /**
     * Método que obtiene todas las categorías de la BD.
     * @param args parámetros del método.
     * @return ObservableList<Categoria>
     */
    public ObservableList<Categoria> getAllCategorias(String[] args) {
        try {
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Categoria", "getCategorias", args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(respuestaServer);
            JsonNode dataNode = objectMapper.readTree(jsonNode.get("data").asText());

            ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

            for (JsonNode node : dataNode) {
                int id = node.get("id").asInt();
                String descripcion = node.get("descripcion").asText();


                Categoria c = new Categoria(id, descripcion);
                listaCategorias.add(c);
            }

            return listaCategorias;

        } catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Error con el servidor", "Ocurrió un error en el servidor: " + e, Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método que comprueba si una categoría tiene asignada uno o varios anuncios.
     * @param args parámetros del método.
     * @return true / false.
     */
    public boolean tieneAnunciosCategoria(String[] args){
        try {
            // Lanza la petición a tu servicio web
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Categoria", "tieneAnunciosCategoria", args);

            // Obtén la respuesta del servidor
            JSONObject jsonObject = new JSONObject(respuestaServer);

            // Devuelve la respuesta de data como booleano
            if (jsonObject.has("data")) {
                return jsonObject.getBoolean("data");
            } else {
                return false;
            }
        } catch (Exception e) {
            // Si hay alguna excepción, devuelve false
            return false;
        }
    }

    /**
     * Método que inserta una nueva categoría en la BD.
     * @param args parámetros del método.
     * @return true / false.
     */
    public boolean insertCategoria(String[] args) {
        try {
            // Lanza la petición a tu servicio web
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Categoria", "insertCategoria", args);

            // Obtén la respuesta del servidor
            JSONObject jsonObject = new JSONObject(respuestaServer);

            // Devuelve la respuesta de data como booleano
            if (jsonObject.has("data")) {
                return jsonObject.getBoolean("data");
            } else {
                return false;
            }
        } catch (Exception e) {
            // Si hay alguna excepción, devuelve false
            return false;
        }
    }

    /**
     * Método que actualiza una categoría de la BD.
     * @param args parámetros del método.
     * @return true / false.
     */
    public boolean updateCategoria(String[] args) {
        try {
            // Lanza la petición a tu servicio web
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Categoria", "updateCategoria", args);

            // Obtén la respuesta del servidor
            JSONObject jsonObject = new JSONObject(respuestaServer);

            // Devuelve la respuesta de data como booleano
            if (jsonObject.has("data")) {
                return jsonObject.getBoolean("data");
            } else {
                return false;
            }
        } catch (Exception e) {
            // Si hay alguna excepción, devuelve false
            return false;
        }
    }

    /**
     * Método que elimina una categoría existente en la BD.
     * @param args parámetros del método.
     * @return true / false.
     */
    public boolean deleteCategoria(String[] args) {
        try {
            // Lanza la petición a tu servicio web
            String respuestaServer = ServerComunication.comunicacion(urlServer, "Categoria", "deleteCategoria", args);

            // Obtén la respuesta del servidor
            JSONObject jsonObject = new JSONObject(respuestaServer);

            // Devuelve la respuesta de data como booleano
            if (jsonObject.has("data")) {
                return jsonObject.getBoolean("data");
            } else {
                return false;
            }
        } catch (Exception e) {
            // Si hay alguna excepción, devuelve false
            return false;
        }
    }

    /**
     * Método que obtiene todos los pedidos registrados en la BD.
     * @param args parámetros del método.
     * @return ObservableList<Pedido>
     */
    public ObservableList<Pedido> getAllPedidos(String[] args) {
        try{
            String respuestaServer = ServerComunication.comunicacion(urlServer,"Pedido","getAllPedidos",args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(respuestaServer);
            String dataString = jsonNode.get("data").asText();
            JsonNode dataNode = objectMapper.readTree(dataString);
            ObservableList<Pedido>listaPedidos =  FXCollections.observableArrayList();

            for (JsonNode node : dataNode) {
                int id = node.get("id").asInt();
                String nombUsu = node.get("nomb_usu").asText();
                int idAnuncio = node.get("id_anuncio").asInt();
                String titulo = node.get("titulo").asText();
                String direccion = node.get("direccion").asText();
                String ciudad = node.get("ciudad").asText();
                String cp = node.get("cp").asText();
                Pedido pedido = new Pedido(id, nombUsu, idAnuncio, titulo, direccion, ciudad, cp);
                listaPedidos.add(pedido);
            }

            return listaPedidos;

        } catch (Exception e) {
            // Manejar la excepción de procesamiento JSON
            Util.mostrarDialogo("Server_Error", "Error con el servidor","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método que obtiene el uso total de la app.
     * @param Logs parámetros del método.
     * @return ObservableList<PieChart.Data>
     */
    public ObservableList<PieChart.Data> obtenerUsoTotal(ArrayList<String>Logs){
        try{
            ObservableList<PieChart.Data>piechartData = FXCollections.observableArrayList();
            for(int i = 0; i<Logs.size();i++){
                URL url = new URL(urlServer + "util/log/"+Logs.get(i));
                URLConnection conn = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                int numeroLineas = 0;
                while (reader.readLine() != null) {
                    numeroLineas++;
                }
                String[]ficheroParseado = Logs.get(i).split("_");
                piechartData.add(new PieChart.Data(ficheroParseado[1].substring(0,2)+"/"+ficheroParseado[1].substring(2,6),numeroLineas));
                reader.close();
            }
            return piechartData;
        }catch (Exception e){
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método que obtiene los Logs del servidor
     * @return ArrayList<String>
     */
    public ArrayList<String> obtenerLogs() {
        try {
            // Crear una instancia de URL
            URL url = new URL(urlServer + "util/log/");
            URLConnection conn = url.openConnection();
            // Obtener el flujo de entrada para leer la respuesta
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            ArrayList<String> lista = new ArrayList<>();
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Filtrar las líneas que contienen nombres de archivos
                if (linea.contains("<a href=") && linea.contains(".txt")) {
                    lista.add(obtenerHrefs(linea));
                }
            }
            reader.close();
            return lista;
        } catch (IOException e) {
            Util.mostrarDialogo("Server_Error", "Ha ocurrido un error.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return null;
        }
    }

    /**
     * Método estático que cuenta el número de veces que aparece un estado específico
     * en un registro de peticiones (log).
     *
     * @param log El registro de peticiones en formato de cadena de texto.
     * @param estado El estado específico que se desea contar en el registro.
     * @return El número de veces que aparece el estado especificado en el registro.
     */
    private static int contarPeticiones(String log, String estado) {
        int contador = 0;
        String[] lineas = log.split("\n");
        for (String linea : lineas) {
            if (linea.contains(estado)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Método estático que busca y devuelve el primer enlace (href) encontrado en un bloque de HTML.
     *
     * @param html El bloque de HTML del cual se desea extraer el enlace.
     * @return El enlace (href) encontrado en el bloque de HTML, o null si no se encuentra ninguno.
     */
    private static String obtenerHrefs(String html) {
        Pattern pattern = Pattern.compile("<a\\s+href=\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Método estático que extrae todas las direcciones IP únicas encontradas en un registro de log.
     *
     * @param log El registro de log que contiene las direcciones IP.
     * @return Un ArrayList que contiene todas las direcciones IP únicas encontradas en el log.
     */
    public static ArrayList<String> getAllIp(String log) {
        // Usar un HashSet para eliminar duplicados automáticamente
        HashSet<String> uniqueIPsSet = new HashSet<>();

        // Patrón para buscar direcciones IP en el registro
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log);

        while (matcher.find()) {
            uniqueIPsSet.add(matcher.group());
        }

        // Convertir el HashSet a ArrayList
        return new ArrayList<>(uniqueIPsSet);
    }

}
