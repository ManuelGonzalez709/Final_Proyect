package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Pedido;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de la tabla de pedidos. Maneja la lógica para cargar y mostrar
 * pedidos en una tabla.
 *
 * @author AnunciaYa
 */
public class PedidoController implements Initializable {

    /**
     * Lista observable de pedidos, que se usa para llenar la tabla.
     */
    private ObservableList<Pedido> pedidosList;

    /**
     * Componente TableView que muestra la lista de pedidos.
     */
    @FXML
    private TableView<Pedido> tbPedidos;

    /**
     * Columna de la tabla que muestra el identificador del pedido.
     */
    @FXML
    private TableColumn<Pedido, Integer> tcIdPedido;

    /**
     * Columna de la tabla que muestra el nombre del usuario que realizó el pedido.
     */
    @FXML
    private TableColumn<Pedido, String> tcNombUsu;

    /**
     * Columna de la tabla que muestra el identificador del anuncio asociado con el pedido.
     */
    @FXML
    private TableColumn<Pedido, Integer> tcIdAnuncio;

    /**
     * Columna de la tabla que muestra el título del anuncio asociado con el pedido.
     */
    @FXML
    private TableColumn<Pedido, String> tcTitulo;

    /**
     * Columna de la tabla que muestra la dirección asociada con el pedido.
     */
    @FXML
    private TableColumn<Pedido, String> tcDireccion;

    /**
     * Columna de la tabla que muestra la ciudad asociada con el pedido.
     */
    @FXML
    private TableColumn<Pedido, String> tcCiudad;

    /**
     * Columna de la tabla que muestra el código postal asociado con el pedido.
     */
    @FXML
    private TableColumn<Pedido, String> tcCp;

    /**
     * Inicializa el controlador después de cargar el archivo FXML.
     * Este método se llama automáticamente cuando se carga la vista.
     *
     * @param url URL de la ubicación utilizada para resolver rutas relativas del objeto raíz o nulo si no se conoce.
     * @param resourceBundle El recurso utilizado para localizar el objeto raíz o nulo si no se localiza.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrepararTabla();
        CargarDatosTabla();
    }

    /**
     * Carga los datos de pedidos en la tabla. Utiliza la clase {@link Metodos} para obtener
     * todos los pedidos y los asigna a la tabla.
     */
    private void CargarDatosTabla() {
        Metodos me = new Metodos();
        // Obtiene todos los pedidos utilizando un método en la clase Metodos
        pedidosList = me.getAllPedidos(new String[]{""});
        // Establece la lista de pedidos en la tabla
        tbPedidos.setItems(pedidosList);
    }

    /**
     * Prepara la tabla de pedidos configurando las columnas y sus respectivos
     * atributos. Configura cada columna para que muestre los datos correspondientes
     * de la clase {@link Pedido}.
     */
    private void PrepararTabla() {
        // Configura las columnas de la tabla para que correspondan a los atributos de Pedido
        tcIdPedido.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcIdPedido.setResizable(false);

        tcNombUsu.setCellValueFactory(new PropertyValueFactory<>("nombUsu"));
        tcNombUsu.setResizable(false);

        tcIdAnuncio.setCellValueFactory(new PropertyValueFactory<>("idAnuncio"));
        tcIdAnuncio.setResizable(false);

        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcTitulo.setResizable(false);

        tcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tcDireccion.setResizable(false);

        tcCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        tcCiudad.setResizable(false);

        tcCp.setCellValueFactory(new PropertyValueFactory<>("cp"));
        tcCp.setResizable(false);
    }
}
