package es.localhost.anunciaya.administrador.util;

import es.localhost.anunciaya.administrador.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Executable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Metodos {
    private String urlServer = "http://192.168.18.5/sv-php";
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
    public ObservableList<PieChart.Data>usoDatosIp(String fichero){
        try{
            ObservableList<PieChart.Data>piechartData = FXCollections.observableArrayList();
            URL url = new URL(urlServer+"/util/log/"+fichero);
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
        }catch (Exception e){return null;}
    }
    public ObservableList<PieChart.Data>obtenerEstadosPeticiones(String fichero){
        try{
            ObservableList<PieChart.Data>piechartData = FXCollections.observableArrayList();
                URL url = new URL(urlServer+"/util/log/"+fichero);
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
    public ObservableList<PieChart.Data> obtenerUsoTotal(ArrayList<String>Logs){
        try{
            ObservableList<PieChart.Data>piechartData = FXCollections.observableArrayList();
            for(int i = 0; i<Logs.size();i++){
                URL url = new URL(urlServer+"/util/log/"+Logs.get(i));
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
            URL url = new URL(urlServer+"/util/log/");
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
            e.printStackTrace();
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
