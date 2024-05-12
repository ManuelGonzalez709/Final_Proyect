package com.example.anunciaya.tools;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class ServerComunication {
    private String urlServer = "http://192.168.0.102/sv-php";
    private String resultadoServer = "";


    public ServerComunication(String server){urlServer = server;}

    public ServerComunication(){}

    public String getResultadoServer() {return resultadoServer;}

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

    public String subirFotoServer(String rutaFoto,int idUsuario,int idAnuncio){
        String server = "files.000webhost.com";
        int port = 21;
        String user = "sv-anunciaya";
        String password = "0O&67mG{oE";
        String remoteDirectory = "/public_html/sv-php/img";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File(rutaFoto);
            String fileName = idAnuncio+"_"+idUsuario+"_"+file.getName();
            FileInputStream inputStream = new FileInputStream(file);

            boolean uploaded = ftpClient.storeFile(remoteDirectory + "/" + fileName, inputStream);
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
    public String subirFoto(String url, int idUsuario,int idAnuncio){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {resultadoServer = subirFotoServer(url,idUsuario,idAnuncio);}
        });
        // lanzamos el hilo y esperamos a que termine
        try{thread.start();thread.join(); return resultadoServer;}
        catch (Exception e){return null;}
    }

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
