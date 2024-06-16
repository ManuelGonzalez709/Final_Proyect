package com.example.anunciaya.tools;
/**
 * @Description Esto es una clase que se usa para establecer comunicacion con el server
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
import android.util.Log;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
/*Esto es una clase que se usa para comunicar con el servidor*/
public class ServerComunication {
    /*Estos son los atributos de la clase*/
    private String urlServer = "https://40945016.servicio-online.net/sv-php/";
    private String resultadoServer = "";
    private String urlServerFtp = "82.194.68.93";
    private int PuertoFTP = 21;
    private String userFtp = "user-10454915";
    private String passFtp = "0O&67mG{oE";
    private String rutaFtp = "/httpdocs/sv-php/img";

    /**
     * Esto es el contructor principal de la clase
     * @param server contiene la Url del Server
     */
    public ServerComunication(String server){urlServer = server;}

    /**
     * Constructor vacío
     */
    public ServerComunication(){}

    /**
     * Esto es un metodo que retorna el resultado del server
     * @return retorna un String
     */
    public String getResultadoServer() {return resultadoServer;}

    /**
     * Esto es un metodo que se encarga de establecer conexion con el server php y manda los datos necesarios
     * @param UrlServer contiene la url del server
     * @param clase contiene la clase que vamos a ejecutar del server
     * @param metodo contiene el metodo que vamos a ejecutar del server
     * @param params contiene los parametros necesarios para la ejecucucion de dicho metodo de la clase
     * @return retorna el resultado del server
     */
    private String comunicacion(String UrlServer , String clase, String metodo , String[]params){
        try {
            // URL del servidor PHP
            URL url = new URL(UrlServer + "/index.php");

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

    /**
     * Esto es un metodo que se encarga de establecer los parametros pasados por el server
     * @param params contiene los parametros pasados por el metodo de comunicacion
     * @return retorna los datos parseados de forma correcta para ser leidos por el JSON
     */
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

    /**
     * Esto es un metodo que se encarga de lanzar la peticion mediante un hilo ya que android studio no permite
     * realizar ejecuciones HTTP y HTTPS en el Hilo principal
     * @param clase contiene la clase que va a ser lanzada por el metodo comunicacion
     * @param metodo  contiene el metodo que va a ser lanzado por el metodo comunicacion
     * @param parametros  contiene los parametros que necesita el metodo  que va a ser lanzado por el metodo comunicacion
     * @return retorna si la ejecucion del hilo ha sido true o no
     */
    public Boolean LanzarPeticion(String clase , String metodo ,String[]parametros){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                resultadoServer = comunicacion(urlServer,clase,metodo,parametros);
            }
        });
        // lanzamos el hilo y esperamos a que termine
        try{thread.start();thread.join();return true;}
        catch (Exception e){return false;}
    }

    /**
     * Esto es un metodo que se ecnarga de borrar las fotos que tiene un anuncio del Server
     * @param idAnuncio contiene el Id del Anuncio que se va a borrar las fotos
     * @param Salvadas contiene un array de las fotos que van a ser salvadas
     * @return retorna si la ejecucion ha sido exitosa o no
     */
    public Boolean borrarFotoServer(String idAnuncio,ArrayList<String>Salvadas){
        FTPClient ftpClient2 = new FTPClient();
        try {
            ftpClient2.connect(urlServerFtp, PuertoFTP);
            ftpClient2.login(userFtp, passFtp);
            ftpClient2.enterLocalPassiveMode();
            ftpClient2.setFileType(FTP.BINARY_FILE_TYPE);

            // Cambiamos al directorio donde están los archivos
            ftpClient2.changeWorkingDirectory(rutaFtp);

            // Obtenemos la lista de nombres de archivos
            String[] archivos = ftpClient2.listNames();

            // Iteramos sobre los archivos y eliminamos aquellos que comiencen con "15_"
            if (archivos != null) {
                for (String archivo : archivos) {
                    if (archivo.startsWith(idAnuncio+"_") && !Salvadas.contains(archivo)) {
                        ftpClient2.deleteFile(archivo);
                    }
                }
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();return false;
        } finally {
            try {
                if (ftpClient2.isConnected()) {
                    ftpClient2.logout();
                    ftpClient2.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Esto es un metodo que se encarga de subir fotos al servidor
     * @param rutaFoto contiene la url de la foto que se va a subir
     * @param idUsuario contiene el id del usuario que ha creado la foto
     * @param idAnuncio contiene el id del anuncio del que se va a subir la foto
     * @return retorna la ruta en red de la foto subida
     */
    public String subirFotoServer(String rutaFoto,int idUsuario,int idAnuncio){
        FTPClient ftpClient = new FTPClient();
        //String serverFTPSubida = "http://sv-anunciaya.000webhostapp.com/sv-php";
        try {
            ftpClient.connect(urlServerFtp, PuertoFTP);
            ftpClient.login(userFtp, passFtp);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File(rutaFoto);
            String fileName = idAnuncio+"_"+idUsuario+"_"+file.getName();
            FileInputStream inputStream = new FileInputStream(file);

            boolean uploaded = ftpClient.storeFile(rutaFtp + "/" + fileName, inputStream);
            inputStream.close();
            if (uploaded) {
                return urlServer+"/img/"+fileName;
            } else {
                return null;
            }

        } catch (IOException ex) {
            Log.i("ErrorFtp",ex.toString());
            return null;
        }finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Es un metodo que se encarga de borrar las fotos del Servidor
     * @param id contiene el id del anuncio del que se va a borrar las fotos
     * @param Salvadas contiene una lista de las fotos que no van a ser borradas
     * @return retorna si el borrado ha sido exitoso o no
     */
    public Boolean borrarFotos(String id, ArrayList<String>Salvadas){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {borrarFotoServer(id,Salvadas);}
        });
        // lanzamos el hilo y esperamos a que termine
        try{thread.start();thread.join(); return true;}
        catch (Exception e){return false;}
    }

    /**
     * Es un metodo que se encarga de subir fotos al server
     * @param url contiene la url de la foto que va a ser subida
     * @param idUsuario contiene el id del usuario que va a ser subido
     * @param idAnuncio contiene el id del anuncio que va a ser subido
     * @return retorna la url de la foto del server
     */
    public String subirFoto(String url, int idUsuario,int idAnuncio){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {resultadoServer = subirFotoServer(url,idUsuario,idAnuncio);}
        });
        // lanzamos el hilo y esperamos a que termine
        try{thread.start();thread.join(); return resultadoServer;}
        catch (Exception e){return null;}
    }

    /**
     * Esto es un metodo que se encarga de obtener los municipios de cordoba que estan en un xml en el server
     * @param urlServer contiene la url del server donde estan los municipios
     * @return retorna los municipios de cordoba
     */
    private String obtenerMunicipios(String urlServer){
        try {
            String respuesta = "";
            // Crear una URL y establecer la conexión HTTP
            URL url = new URL(urlServer + "/util/municipios.xml");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Leer la respuesta de la solicitud HTTP
            InputStream inputStream = conn.getInputStream();

            // Parsear el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            // Crear un objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Compilar la expresión XPath
            XPathExpression expr = xpath.compile("//municipio");

            // Evaluar la expresión XPath para obtener el resultado
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            // Recorrer los nodos y obtener el texto de cada nodo
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                respuesta+=element.getTextContent()+";";
            }
            return respuesta;

        } catch (Exception e) {
            e.printStackTrace(); return null;
        }
    }

    /**
     * Esto es un metodo que se encarga de retornar los municipos de cordoba , pero para ello hay que lanzar un hilo
     * @return retorna los municipios de cordoba
     */
    public String getMunicipios(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                resultadoServer = obtenerMunicipios(urlServer);
            }
        });
        // lanzamos el hilo y esperamos a que termine
        try{thread.start();thread.join();return resultadoServer;}
        catch (Exception e){return null;}
    }
}
