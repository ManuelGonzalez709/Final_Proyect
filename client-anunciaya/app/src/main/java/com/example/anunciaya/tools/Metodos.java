package com.example.anunciaya.tools;
/**
 * Clase Metodos
 * @Author Carlos Murillo Perez & Manuel Gonzalez Perez
 * @Version 2.1
 * @Description Clase que contiene todas los metodos disponibles
 */

import com.example.anunciaya.adapter.ListAnuncios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.util.ArrayList;

public class Metodos {
    //Atributos de la Clase
    private static ServerComunication comunication;

    /**
     * Constructor Principal
     */
    public Metodos() {comunication = new ServerComunication();}

    /**
     * Metodo que se encarga de Insertar Usuario y retorna el Id del Usuario
     * @param args parametros que necesita el Metodo del Servidor para su Funcionamiento
     * @return retorna el Id del Usuario
     */
    public int insertUsuario(String[]args){
        try{
            if(comunication.LanzarPeticion("Usuario","insertUsuario",args)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                if(jsonObject.getBoolean("data")){ // si sale true o se ha insertado
                    String []nomb_usu = {args[2]};
                    return getIdUser(nomb_usu);
                }else return -1;
            }else return -1;
        }catch (Exception e){return -1;}
    }

    /**
     * Metodo que se encarga de Comprobar si la contraseña y el ususario son validos
     * @param args parametros que necesita el Server
     * @return retorna un boolean (True= Inicio de Sesion Correcto/False= Inicio de sesion Fallido)
     */
    public Boolean verificarAuthCliente(String[]args){
        try{
            comunication.LanzarPeticion("Auth","verificarAuthClient",args);
            JSONObject jsonObject = new JSONObject(getRespuestaServer());
            return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }

    /**
     * Metodo que se encarga de retornar el Id del usuario
     * @param args parametros del server necesarios
     * @return retorna el Id del usuario
     */
    public int getIdUser(String[]args){
        try{
            comunication.LanzarPeticion("Usuario","getIdUser",args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());
            String dataString = jsonNode.get("data").asText();
            JsonNode dataNode = objectMapper.readTree(dataString);
            return dataNode.get("id").asInt();
        }catch (Exception e){return -1;}
    }

    /**
     * Método que obtiene todas las categorias proporcionadas por
     * el método del servidor php getCategorias
     *
     * @return ArrayList<String> todas las categorias
     */
    public ArrayList<String> getCategorias() {
        try {
            comunication.LanzarPeticion("Categoria", "getCategorias", new String[]{""});
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

            // Verificar si el nodo "data" existe y es una cadena JSON válida
            if (jsonNode.has("data")) {
                // Obtener la cadena JSON del nodo "data"
                String dataJsonString = jsonNode.get("data").asText();

                // Parsear la cadena JSON para obtener las descripciones de categorías
                ArrayList<String> categorias = new ArrayList<>();
                JsonNode dataNode = objectMapper.readTree(dataJsonString);
                for (JsonNode node : dataNode) {
                    String descripcion = node.get("descripcion").asText();
                    categorias.add(descripcion);
                }

                // Devolver la lista de categorías
                return categorias;
            } else {
                // Manejar el caso cuando no hay datos disponibles
                return new ArrayList<>();
            }
        } catch (Exception e) {
            // Manejar cualquier excepción y devolver null
            return null;
        }
    }

    /**
     * Método que obtiene los anuncios del inicio eliminando los anuncios del usuario registrado
     * Unicamente se muestran los datos: titulo, precio, divisa, ubicacion y fotos.
     *
     * @param args Id del usuario registrado
     * @return ArrayList<ListAnuncios> con todos los anuncios
     */
    public ArrayList<ListAnuncios> getAnunciosInicio(String[] args) {
        try {
            if (comunication.LanzarPeticion("Anuncio", "getAnunciosExcepIdUsuarioAndPedido", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                ArrayList<ListAnuncios> detallesProductos = new ArrayList<>();

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();
                    if (dataString.equals("null")) {
                        // Si data es null es porque no hay anuncios para mostrar al usuario
                        // porque los anuncios que hay son todos suyos

                        return new ArrayList<>();
                    }

                    JsonNode dataNode = objectMapper.readTree(dataString);
                    for (JsonNode node : dataNode) {
                        int id = node.get("id").asInt();
                        String titulo = node.get("titulo").asText();
                        String precio = node.get("precio").asText();
                        String ubicacion = node.get("ubicacion").asText();
                        String fotos = node.get("fotos").asText();

                        ListAnuncios anuncio = new ListAnuncios(id,titulo, precio, ubicacion, fotos);
                        detallesProductos.add(anuncio);
                    }
                }

                return detallesProductos;
            } else {
                return new ArrayList<>();
            }
        } catch (JsonProcessingException e) {
            // Manejar la excepción de procesamiento JSON
            return null;
        }
    }

    /**
     * Metodo que retorna un anuncio por id
     * @param args argumentos necesarios por el server
     * @return retorna un objeto de tipo anuncio
     */
    public Anuncio getAnuncioId(String[] args) {
        try {
            if (comunication.LanzarPeticion("Anuncio", "getAnuncioId", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();
                    if (dataString.equals("null")) {
                        // Si data es null es porque no hay anuncios con el id especificado
                        return null;
                    }

                    // Leer cada campo individualmente y asignarlo al objeto Anuncio
                    JsonNode dataNode = objectMapper.readTree(dataString);
                    Anuncio anuncio = new Anuncio();
                    if (dataNode.has("id")) {
                        anuncio.setId(dataNode.get("id").asInt());
                    }
                    if (dataNode.has("id_usuario")) {
                        anuncio.setIdUsuario(dataNode.get("id_usuario").asInt());
                    }
                    if (dataNode.has("id_categoria")) {
                        anuncio.setidCategoria(dataNode.get("id_categoria").asInt());
                    }
                    if (dataNode.has("titulo")) {
                        anuncio.setTitulo(dataNode.get("titulo").asText());
                    }
                    if (dataNode.has("descripcion")) {
                        anuncio.setDescripcion(dataNode.get("descripcion").asText());
                    }
                    if (dataNode.has("estado")) {
                        anuncio.setEstado(dataNode.get("estado").asText());
                    }
                    if (dataNode.has("ubicacion")) {
                        anuncio.setUbicacion(dataNode.get("ubicacion").asText());
                    }
                    if (dataNode.has("precio")) {
                        anuncio.setPrecio(dataNode.get("precio").asText());
                    }
                    if (dataNode.has("fotos")) {
                        anuncio.setFotos(dataNode.get("fotos").asText());
                    }

                    return anuncio;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {
            // Manejar la excepción de procesamiento JSON
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que retorna el Id del Ultimo anuncio creado por el usuario
     * @param args parametros necesarios por el server
     * @return retorna el Id del ultimo anuncio creado por el usuario x(id)
     */
    public int getIdNewAnuncioUsuario(String[]args){
        try{
            if(comunication.LanzarPeticion("Anuncio","getIdNewAnuncioUsuario",args)){
                JSONObject response = new JSONObject(getRespuestaServer());
                String dataString = response.getString("data");
                JSONObject dataObject = new JSONObject(dataString);
                return dataObject.getInt("ultimo_id");
            }
            return -1;
        }catch (Exception e){return -1;}
    }

    /**
     * Metodo que se encarga de retornar el Id de la Categoria por Descripcion
     * @param args parametros necesarios por el server
     * @return retorna el Id de la Categoria
     */
    public int getCategoriaId(String[] args) {
        try {// Lanzar la petición al servidor y obtener la respuesta
            if (comunication.LanzarPeticion("Categoria", "getCategoriaId", args)) {// Leer la respuesta del servidor como un árbol JSON
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                // Verificar si la respuesta contiene datos
                if (jsonNode.has("data")) {
                    // Obtener la cadena JSON de "data"
                    String dataString = jsonNode.get("data").asText();

                    // Convertir la cadena JSON en un objeto JsonNode
                    JsonNode dataNode = objectMapper.readTree(dataString);

                    // Verificar si el nodo de datos no es nulo
                    if (dataNode != null && dataNode.has("id")) {
                        // Obtener el ID de la categoría
                        return dataNode.get("id").asInt();
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Si ocurre alguna excepción o no se encuentran datos válidos, devolver 0
        return -1;
    }

    /**
     * Metodo que se encarga de Insertar un Anuncio
     * @param args parametros necesarios por el server
     * @return retorna si la inserccion del Anuncio ha sido exitosa o no
     */
    public Boolean insertarAnuncio(String [] args){
        try{
            return comunication.LanzarPeticion("Anuncio","insertAnuncio",args);
        }catch (Exception e){return false;}
    }

    /**
     * Metodo que se encarga de actualizar subir las fotos al server con id_usuario y id_anuncio
     * @param url url de la foto a subir
     * @param idUsuario id del usuario creador del anuncio
     * @param idAnuncio id del anuncio creado por el usuario
     * @return retorna la url del anuncio en el server ya subida
     */
    public String subirFotoServer(String url,int idUsuario,int idAnuncio){
        return comunication.subirFoto(url,idUsuario,idAnuncio);
    }

    /**
     * Metodo que se encarga de actualizar en la base de datos la url de las fotos
     * @param args argumentos necesarios por el server
     * @return retorna si la actualizacion ha sido exitosa o no
     */
    public Boolean updateAnuncioUploadImagen(String[]args){
        try{
            if(comunication.LanzarPeticion("Anuncio","subirFotoBaseDatos",args)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                return jsonObject.getBoolean("data");
            }
            return false;
        }catch (Exception e){return false;}
    }

    /**
     * Metodo que se encarga de obtener la respuesta del server tras ejecutar LanzarPeticion
     * @return retorna la respuesta del Server
     */
    private static String getRespuestaServer(){
        return comunication.getResultadoServer();
    }
}
