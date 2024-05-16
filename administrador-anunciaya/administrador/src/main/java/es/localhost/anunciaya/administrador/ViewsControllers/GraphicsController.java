package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GraphicsController implements Initializable {
    @FXML private PieChart usoApp;
    @FXML private PieChart peticiones;
    @FXML private PieChart masUsados;
    @FXML private Metodos m;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = new Metodos();
        ArrayList<String>logs = m.obtenerLogs();// Obtienes los logs del Servidor
        usoApp.getData().addAll(m.obtenerUsoTotal(logs)); // aplicamos los datos a peticiones meses
        peticiones.getData().addAll(m.obtenerEstadosPeticiones(logs.get(logs.size()-1))); // aplicamos los datos a peticiones meses
        masUsados.getData().addAll(m.usoDatosIp(logs.get(logs.size()-1)));
    }

}