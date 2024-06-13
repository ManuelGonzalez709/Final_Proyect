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

public class Metodos {
    private int IdUser = 0;
    private String urlServer = "https://40945016.servicio-online.net/sv-php/";
    public Metodos(int id){
        IdUser = id;
    }
    public Metodos(){}

    public Boolean updateContras(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","updateContras",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Actualizacion Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }
    public Boolean actualizarUsuario(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","updateUsuario",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Actualizacion Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }
    public Boolean borrarUsuario(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","borrarUsuario",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return false;}
    }
    public Boolean insertarUsuario(String[]params){
        try{
            String resultadoServer = ServerComunication.comunicacion(urlServer,"Usuario","AdminInsertUsuario",params);
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e) {
        Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
        return false;}
    }
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
    public ObservableList<PieChart.Data>usoDatosIp(String fichero){
        try{
            ObservableList<PieChart.Data>piechartData = FXCollections.observableArrayList();
            URL url = new URL(urlServer + "util/log/"+fichero);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lector;String contenidoLog = "";
            while ((lector = reader.readLine()) != null) {
                contenidoLog+=lector+"\n";
            }
            ArrayList<String>ips = getAllIp(contenidoLog);
            for(int i = 0; i<ips.size();i++){
                piechartData.add(new PieChart.Data(ips.get(i),contarPeticiones(contenidoLog,ips.get(i))));
            }

            return piechartData;
        }catch (Exception e){
            Util.mostrarDialogo("Server_Error", "Ha ocurrido un error.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            e.printStackTrace();
            return null;}
    }
    public ObservableList<PieChart.Data>obtenerEstadosPeticiones(String fichero){
        try{
            ObservableList<PieChart.Data>piechartData = FXCollections.observableArrayList();
                URL url = new URL(urlServer+"util/log/"+fichero);
                URLConnection conn = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String lector;String contenidoLog = "";
                while ((lector = reader.readLine()) != null) {
                   contenidoLog+=lector+"\n";
                }
                piechartData.add(new PieChart.Data("OK",contarPeticiones(contenidoLog,"OK")));
                piechartData.add(new PieChart.Data("FAILED",contarPeticiones(contenidoLog,"FAILED")));
                reader.close();
            return piechartData;
        }catch (Exception e){
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e,Alert.AlertType.INFORMATION);
            return null;
        }
    }
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

                Anuncio a = new Anuncio(id, nombUsu, categoria, titulo, descripcion, estado, ubicacion, precio, fotos, fechPublic);
                listaAnuncios.add(a);
            }

            return listaAnuncios;

        } catch (Exception e) {
            Util.mostrarDialogo("Server_Error", "Error con el servidor", "Ocurrió un error en el servidor: " + e, Alert.AlertType.INFORMATION);
            return null;
        }
    }
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
    private static String obtenerHrefs(String html) {
        Pattern pattern = Pattern.compile("<a\\s+href=\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) return matcher.group(1);
        return null;
    }
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
