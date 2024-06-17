package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador para gestionar los gráficos circulares que representan diferentes estadísticas.
 * Implementa {@link Initializable} para manejar la inicialización de componentes FXML.
 *
 * @author AnunciaYa
 */
public class GraphicsController implements Initializable {

    /**
     * Gráfico circular que muestra el uso de la aplicación.
     */
    @FXML
    private PieChart usoApp;

    /**
     * Gráfico circular que muestra las peticiones de la aplicación.
     */
    @FXML
    private PieChart peticiones;

    /**
     * Gráfico circular que muestra los elementos más usados.
     */
    @FXML
    private PieChart masUsados;

    /**
     * Instancia de la clase {@link Metodos} para manejar las operaciones relacionadas con los datos.
     */
    @FXML
    private Metodos m;

    /**
     * Método de inicialización para cargar y aplicar los datos a los gráficos.
     * Este método se llama automáticamente al inicializar la vista.
     *
     * @param url no se utiliza en este contexto.
     * @param resourceBundle no se utiliza en este contexto.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear una nueva instancia de la clase Metodos
        m = new Metodos();

        // Obtener los logs del servidor
        ArrayList<String> logs = m.obtenerLogs();

        // Aplicar los datos obtenidos a los gráficos correspondientes
        usoApp.getData().addAll(m.obtenerUsoTotal(logs)); // Datos para el gráfico de uso total de la aplicación
        peticiones.getData().addAll(m.obtenerEstadosPeticiones(logs.get(logs.size() - 1))); // Datos para el gráfico de estados de peticiones
        masUsados.getData().addAll(m.usoDatosIp(logs.get(logs.size() - 1))); // Datos para el gráfico de elementos más usados
    }
}
