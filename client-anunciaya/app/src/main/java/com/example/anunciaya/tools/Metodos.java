package com.example.anunciaya.tools;
/**
 * @Description Esto es una clase que contiene todos los metodos necesarios por la app
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
import com.example.anunciaya.adapter.ListAnuncios;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.w3c.dom.Node;

import java.util.ArrayList;

/*Esta es la clase principal*/
public class Metodos {
    /*Estos son los atributos de la clase*/
    private static ServerComunication comunication;

    /**
     * Esto es el Constructor de la clase Principal
     */
    public Metodos() {comunication = new ServerComunication();}

    /**
     * Esto es un metodo que se encarga de Insertar un Usuario
     * @param args contiene los datos de la Inserccion
     * @return retorna un entero
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
     * Esto es un metodo que se encarga de verificar un Usuario
     * @param args contiene los datos de la verificacion
     * @return retorna un Booleano con el valor de la Inserccion
     */
    public Boolean verificarAuthCliente(String[]args){
        try{
            comunication.LanzarPeticion("Auth","verificarAuthClient",args);
            JSONObject jsonObject = new JSONObject(getRespuestaServer());
            return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }
    /**
     * Esto es un metodo que se encarga de retornar un Id de Usuario pasado un nombre de Usuario
     * @param args contiene el Nombre de Usuario
     * @return retorna un Id de Usuario
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
                categorias.add("None");

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
     * Método que obtiene los anuncios del inicio eliminando los anuncios del usuario registrado,
     * además de eliminar los anuncios comprados.
     * Unicamente se muestran los datos: titulo, precio, ubicacion y fotos.
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
     * Método que obtiene todos los datos  de un anuncio a partir de un idUsuario
     *
     * @param args Id del Usuario que corresponde con el anuncio
     * @return Anuncio con todos los datos
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
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que obtiene todos los datos  de un usuario a partir de un idUsuario
     *
     * @param args Id del Usuario
     * @return Usuario con todos los datos
     */
    public Usuario getUsuarioDataId(String[] args) {
        try {
            if (comunication.LanzarPeticion("Usuario", "getUsuarioDataId", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();
                    if (dataString.equals("null")) {
                        // Si data es null es porque no hay usuarios con el id especificado
                        return null;
                    }

                    // Leer cada campo individualmente y asignarlo al objeto Usuario
                    JsonNode dataNode = objectMapper.readTree(dataString);
                    Usuario usuario = new Usuario();
                    if (dataNode.has("id")) {
                        usuario.setId(dataNode.get("id").asInt());
                    }
                    if (dataNode.has("nombre")) {
                        usuario.setNombre(dataNode.get("nombre").asText());
                    }
                    if (dataNode.has("apellidos")) {
                        usuario.setApellidos(dataNode.get("apellidos").asText());
                    }
                    if (dataNode.has("nomb_usu")) {
                        usuario.setNombUsu(dataNode.get("nomb_usu").asText());
                    }
                    if (dataNode.has("contras")) {
                        usuario.setContras(dataNode.get("contras").asText());
                    }
                    if (dataNode.has("fecha_nacimiento")) {
                        usuario.setFechaNacimiento(dataNode.get("fecha_nacimiento").asText());
                    }
                    if (dataNode.has("email")) {
                       usuario.setEmail(dataNode.get("email").asText());
                    }
                    if (dataNode.has("telefono")) {
                        usuario.setTelefono(dataNode.get("telefono").asText());
                    }
                    if (dataNode.has("tipo")) {
                        usuario.setTipo(dataNode.get("tipo").asText());
                    }
                    if (dataNode.has("foto")) {
                        usuario.setFoto(dataNode.get("foto").asText());
                    }

                    return usuario;
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
     * Método que obtiene todos los anuncios a partir de un id categoria
     * @param args id Categoria
     * @return ArrayList<ListAnuncios>
     */
    public ArrayList<ListAnuncios> getAnunciosIdCategoria(String[] args) {
        try {
            if (comunication.LanzarPeticion("Anuncio", "getAnunciosIdCategoria", args)) {
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
     * Método que obtiene todos los anuncios a partir de la ubicacion
     * @param args ubicacion, idUsuario
     * @return ArrayList<ListAnuncios>
     */
    public ArrayList<ListAnuncios> getAnunciosUbicacion(String[] args){
        try {
            if (comunication.LanzarPeticion("Anuncio", "getAnunciosUbicacion", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                ArrayList<ListAnuncios> detallesProductos = new ArrayList<>();

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();

                    // Si data es null es porque no hay anuncios con
                    // la ubicacion seleccionada
                    if (dataString.equals("null")) {return null;}

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
            } else {return null;}
        } catch (JsonProcessingException e) {return null;}
    }

    /**
     * Método que obtiene todos los anuncios a partir de la ubicacion y del id_categoria
     * @param args ubicacion, idCategoria, idUsuario
     * @return ArrayList<ListAnuncios>
     */
    public ArrayList<ListAnuncios> getAnunciosUbicacionCategoria(String[] args){
        try {
            if (comunication.LanzarPeticion("Anuncio", "getAnunciosUbicacionCategoria", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                ArrayList<ListAnuncios> detallesProductos = new ArrayList<>();

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();

                    // Si data es null es porque no hay anuncios con
                    // la ubicacion seleccionada
                    if (dataString.equals("null")) {return null;}

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
            } else {return null;}
        } catch (JsonProcessingException e) {return null;}
    }

    /**
     * Método que obtiene la descripcion de la categoria mediante un id Categoria
     * @param args id Categoria
     * @return descripcion categoria
     */
    public String getCategoriaDescripcion(String[] args) {
        try {
            String descripcion = null;
            if (comunication.LanzarPeticion("Categoria", "getCategoriaDescripcion", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText(); // Obtener la cadena JSON de "data"
                    JsonNode dataNode = objectMapper.readTree(dataString); // Convertir la cadena JSON en un objeto JsonNode

                    if (dataNode != null) {
                        descripcion = dataNode.get("descripcion").asText(); // obtener descripcion
                    }
                }
                return descripcion;
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método que obtiene el id categoria de una categoria mediante la descripcion
     * @param args descripcion
     * @return id categoria
     */
    public int getCategoriaId(String[] args) {
        try {
            // Lanzar la petición al servidor y obtener la respuesta
            if (comunication.LanzarPeticion("Categoria", "getCategoriaId", args)) {
                // Leer la respuesta del servidor como un árbol JSON
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
     * Método que inserta un nuevo pedido
     * @param args id_anuncio, id_comprador, direccion, ciudad, cp
     * @return true | false
     */
    public boolean insertPedido(String[]args){
        try{
            if(comunication.LanzarPeticion("Pedido","insertPedido",args)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                // Si devuelve true se ha creado el pedido
                return jsonObject.getBoolean("data");
            }else return false;
        }catch (Exception e){return false;}
    }

    /**
     * Metodo que se encarga de Insertar un Anuncio
     * @param args parametros necesarios por el server
     * @return retorna si la inserccion del Anuncio ha sido exitosa o no
     */
    public Boolean insertarAnuncio(String [] args){
        try{
            if(comunication.LanzarPeticion("Anuncio","insertAnuncio",args)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                // Si devuelve true se ha creado el anuncio
                return jsonObject.getBoolean("data");
            }else return false;
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
     * Metodo que se encarga de borrar las Fotos por Id de Anncio
     * @param id contiene el Id del Anuncio de las Fotos que quieres borrar
     * @param Salvadas contiene una lista de las Imagenes que no hay que borrar
     * @return retorna si el Borrado ha sido true o false
     */
    public Boolean borrarFotosIdAnuncio(String id,ArrayList<String>Salvadas){
        if(comunication.borrarFotos(id,Salvadas))return true;
        else return false;
    }
    public ArrayList<ListAnuncios> getAnunciosIdUsuario(String[] args) {
        try {
            if (comunication.LanzarPeticion("Anuncio", "getAnunciosIdUsuario", args)) {
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
     * Método que obtiene los anuncios que ha comprado el usuario.
     * Unicamente se muestran los datos: titulo, precio, direccion, ciudad y fotos.
     *
     * @param args Id del usuario comprador
     * @return ArrayList<ListAnuncios> con todos los anuncios
     */
    public ArrayList<ListAnuncios> getPedidos(String[] args) {
        try {
            if (comunication.LanzarPeticion("Pedido", "getPedidos", args)) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());

                ArrayList<ListAnuncios> detallesProductos = new ArrayList<>();

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();
                    if (dataString.equals("null")) {
                        return null;
                    }

                    JsonNode dataNode = objectMapper.readTree(dataString);
                    for (JsonNode node : dataNode) {
                        String titulo = node.get("titulo").asText();
                        String precio = node.get("precio").asText();
                        String direccion = node.get("direccion").asText();
                        String ciudad = node.get("ciudad").asText();
                        String fotos = node.get("fotos").asText();

                        ListAnuncios anuncio = new ListAnuncios(titulo, precio, direccion, ciudad, fotos);
                        detallesProductos.add(anuncio);
                    }
                }

                return detallesProductos;
            } else {
                return null;
            }
        } catch (JsonProcessingException e) {return null;}
    }

    /**
     * Metodo que se encarga de retornar los Envios por usuario
     * @param args contiene el Id del Usuario
     * @return retorna una lista de Anuncios
     */
    public ArrayList<ListAnuncios> getEnvios(String[] args) {
        try {
            if (comunication.LanzarPeticion("Anuncio", "getEnvios", args)) {
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
     * Metodo que se encarga de Actualizar la contraseña del Usuario
     * @param params contiene el Id del Usuario y la contraseña
     * @return retorna si la actualizacion ha sido exitosa o no
     */
    public Boolean ActualizarContasena(String[]params){
        try{
            if(comunication.LanzarPeticion("Usuario","updateContras",params)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                return jsonObject.getBoolean("data");
            }else return false;
        }catch (Exception e){return false;}
    }

    /**
     * Esto es un metodo que se encarga de Actualizar el Usuario
     * @param params contiene los parametros de los nuevos datos del Usuario
     * @return retorna si la actualizacion ha sido exitosa o no
     */
    public Boolean ActualizarUsuario(String[]params){
        try{
            if(comunication.LanzarPeticion("Usuario","updateUsuario",params)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                return jsonObject.getBoolean("data");
            }else return false;
        }catch (Exception e){return false;}
    }

    /**
     * Estro es un kmetodo que se encarga de borrar el Anuncio
     * @param args contiene el Id del Usuario que queremos borrar
     * @return contiene los datos del Anuncio que queremos borrar
     */
    public String deleteAnuncio(String[] args) {
        try {
            // Lanza la petición a tu servicio web
            if (comunication.LanzarPeticion("Anuncio", "deleteAnuncio", args)) {
                // Obtén la respuesta del servidor
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                // Devuelve la respuesta de data
                return jsonObject.getString("data");
            } else {
                return "false";
            }
        } catch (Exception e) {
            // Si hay alguna excepción, devuelve "false" como String
            return "false";
        }
    }

    /**
     * Metodo que se encarga de Actualizar el Anuncio
     * @param args contiene los datos de Actualizacion del Anuncio
     * @return retorna si la Actualizacion ha sido exitosa o no
     */
    public boolean updateAnuncio(String[] args){
        try{
            if(comunication.LanzarPeticion("Anuncio", "updateAnuncio", args)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                return jsonObject.getBoolean("data");
            } else return false;
        } catch (Exception e){return false;}
    }

    /**
     * Retorna la respuesta dada por el servidor (Hilo)
     * @return retorna la respuesta del Server
     */
    private String getRespuestaServer(){
        return comunication.getResultadoServer();
    }
}
