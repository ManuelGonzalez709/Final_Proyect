package com.example.anunciaya.tools;


import com.example.anunciaya.adapter.ListAnuncios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.util.ArrayList;

public class Metodos {
    private static ServerComunication comunication;
    public Metodos() {comunication = new ServerComunication();}

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

    public Boolean verificarAuthCliente(String[]args){
        try{
            comunication.LanzarPeticion("Auth","verificarAuthClient",args);
            JSONObject jsonObject = new JSONObject(getRespuestaServer());
            return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }

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
            if (comunication.LanzarPeticion("Anuncio", "getAnunciosExcepIdUsuario", args)) {
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
                        String divisa = node.get("divisa").asText();
                        String ubicacion = node.get("ubicacion").asText();
                        String fotos = node.get("fotos").asText();

                        ListAnuncios anuncio = new ListAnuncios(id,titulo, precio, divisa, ubicacion, fotos);
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
                    if (dataNode.has("divisa")) {
                        anuncio.setDivisa(dataNode.get("divisa").asText());
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


    private static String getRespuestaServer(){
        return comunication.getResultadoServer();
    }
}
