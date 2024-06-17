package es.localhost.anunciaya.administrador.util;
/**
 * @Description Esto es una clase que contiene todos los metodos necesarios por la app
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.localhost.anunciaya.administrador.ListAnuncios;
import es.localhost.anunciaya.administrador.User;
import org.json.JSONObject;

import java.util.ArrayList;

/*Esta es la clase principal*/
public class MetodosCliente {
    /*Estos son los atributos de la clase*/

    String urlServer = "https://40945016.servicio-online.net/sv-php/";
    /**
     * Esto es el Constructor de la clase Principal
     */
    public MetodosCliente() {}

    /**
     * Esto es un metodo que se encarga de Insertar un Usuario
     * @param args contiene los datos de la Inserccion
     * @return retorna un entero
     */
    public int insertUsuario(String[]args){
        try{
            String res = ServerComunication.comunicacion(urlServer,"Usuario","insertUsuario",args);
                JSONObject jsonObject = new JSONObject(res);
                if(jsonObject.getBoolean("data")){ // si sale true o se ha insertado
                    String []nomb_usu = {args[2]};
                    return getIdUser(nomb_usu);
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
            String res =  ServerComunication.comunicacion(urlServer,"Auth","verificarAuthClient",args);
            JSONObject jsonObject = new JSONObject(res);
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
            String res =   ServerComunication.comunicacion(urlServer,"Usuario","getIdUser",args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(res);
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
            String res =   ServerComunication.comunicacion(urlServer,"Categoria", "getCategorias", new String[]{""});
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(res);

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
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio", "getAnunciosExcepIdUsuarioAndPedido", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
        } catch (JsonProcessingException e) {
            // Manejar la excepción de procesamiento JSON
            return null;
        }
    }
    /**
     * Método que obtiene todos los datos  de un usuario a partir de un idUsuario
     *
     * @param args Id del Usuario
     * @return Usuario con todos los datos
     */
    public User getUsuarioDataId(String[] args) {
        try {
            String res = ServerComunication.comunicacion(urlServer,"Usuario", "getUsuarioDataId", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText();
                    if (dataString.equals("null")) {
                        // Si data es null es porque no hay usuarios con el id especificado
                        return null;
                    }

                    // Leer cada campo individualmente y asignarlo al objeto Usuario
                    JsonNode dataNode = objectMapper.readTree(dataString);
                    User usuario = new User();
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
                        usuario.setNomb_usu(dataNode.get("nomb_usu").asText());
                    }
                    if (dataNode.has("fecha_nacimiento")) {
                        usuario.setFecha_nacimiento(dataNode.get("fecha_nacimiento").asText());
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
                    return usuario;
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
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio", "getAnunciosIdCategoria", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio", "getAnunciosUbicacion", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
        } catch (JsonProcessingException e) {return null;}
    }

    /**
     * Método que obtiene todos los anuncios a partir de la ubicacion y del id_categoria
     * @param args ubicacion, idCategoria, idUsuario
     * @return ArrayList<ListAnuncios>
     */
    public ArrayList<ListAnuncios> getAnunciosUbicacionCategoria(String[] args){
        try {
            String res =   ServerComunication.comunicacion(urlServer,"Anuncio", "getAnunciosUbicacionCategoria", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
            String res =  ServerComunication.comunicacion(urlServer,"Categoria", "getCategoriaDescripcion", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

                if (jsonNode.has("data")) {
                    String dataString = jsonNode.get("data").asText(); // Obtener la cadena JSON de "data"
                    JsonNode dataNode = objectMapper.readTree(dataString); // Convertir la cadena JSON en un objeto JsonNode

                    if (dataNode != null) {
                        descripcion = dataNode.get("descripcion").asText(); // obtener descripcion
                    }
                }
                return descripcion;
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
            String res =  ServerComunication.comunicacion(urlServer,"Categoria", "getCategoriaId", args);
                // Leer la respuesta del servidor como un árbol JSON
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio","getIdNewAnuncioUsuario",args);
                JSONObject response = new JSONObject(res);
                String dataString = response.getString("data");
                JSONObject dataObject = new JSONObject(dataString);
                return dataObject.getInt("ultimo_id");
        }catch (Exception e){return -1;}
    }

    /**
     * Método que inserta un nuevo pedido
     * @param args id_anuncio, id_comprador, direccion, ciudad, cp
     * @return true | false
     */
    public boolean insertPedido(String[]args){
        try{
            String res =  ServerComunication.comunicacion(urlServer,"Pedido","insertPedido",args);
                JSONObject jsonObject = new JSONObject(res);
                // Si devuelve true se ha creado el pedido
                return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }

    /**
     * Metodo que se encarga de Insertar un Anuncio
     * @param args parametros necesarios por el server
     * @return retorna si la inserccion del Anuncio ha sido exitosa o no
     */
    public Boolean insertarAnuncio(String [] args){
        try{
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio","insertAnuncio",args);
                JSONObject jsonObject = new JSONObject(res);
                // Si devuelve true se ha creado el anuncio
                return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }
    /**
     * Metodo que se encarga de actualizar en la base de datos la url de las fotos
     * @param args argumentos necesarios por el server
     * @return retorna si la actualizacion ha sido exitosa o no
     */
    public Boolean updateAnuncioUploadImagen(String[]args){
        try{
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio","subirFotoBaseDatos",args);
                JSONObject jsonObject = new JSONObject(res);
                return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }

    public ArrayList<ListAnuncios> getAnunciosIdUsuario(String[] args) {
        try {
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio", "getAnunciosIdUsuario", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
            String res =   ServerComunication.comunicacion(urlServer,"Pedido", "getPedidos", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
        } catch (JsonProcessingException e) {return null;}
    }

    /**
     * Metodo que se encarga de retornar los Envios por usuario
     * @param args contiene el Id del Usuario
     * @return retorna una lista de Anuncios
     */
    public ArrayList<ListAnuncios> getEnvios(String[] args) {
        try {
            String res =   ServerComunication.comunicacion(urlServer,"Anuncio", "getEnvios", args);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res);

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
               String res =  ServerComunication.comunicacion(urlServer,"Usuario","updateContras",params);
                JSONObject jsonObject = new JSONObject(res);
                return jsonObject.getBoolean("data");
          
        }catch (Exception e){return false;}
    }

    /**
     * Esto es un metodo que se encarga de Actualizar el Usuario
     * @param params contiene los parametros de los nuevos datos del Usuario
     * @return retorna si la actualizacion ha sido exitosa o no
     */
    public Boolean ActualizarUsuario(String[]params){
        try{
            String res = ServerComunication.comunicacion(urlServer,"Usuario","updateUsuario",params);
                JSONObject jsonObject = new JSONObject(res);
                return jsonObject.getBoolean("data");
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
            String res =   ServerComunication.comunicacion(urlServer,"Anuncio", "deleteAnuncio", args);
                // Obtén la respuesta del servidor
                JSONObject jsonObject = new JSONObject(res);
                // Devuelve la respuesta de data
                return jsonObject.getString("data");
            
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
            String res =  ServerComunication.comunicacion(urlServer,"Anuncio", "updateAnuncio", args);
                JSONObject jsonObject = new JSONObject(res);
                return jsonObject.getBoolean("data");
          
        } catch (Exception e){return false;}
    }
}
