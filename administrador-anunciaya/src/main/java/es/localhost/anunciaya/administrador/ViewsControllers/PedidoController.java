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

public class PedidoController implements Initializable {
    private ObservableList<Pedido> pedidosList;
    @FXML
    private TableView<Pedido> tbPedidos;
    @FXML private TableColumn<Pedido, Integer> tcIdPedido;
    @FXML private TableColumn<Pedido, String> tcNombUsu;
    @FXML private TableColumn<Pedido, Integer> tcIdAnuncio;
    @FXML private TableColumn<Pedido, String> tcTitulo;
    @FXML private TableColumn<Pedido, String> tcDireccion;
    @FXML private TableColumn<Pedido, String> tcCiudad;
    @FXML private TableColumn<Pedido, String> tcCp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrepararTabla();
        CargarDatosTabla();
    }

    private void CargarDatosTabla(){
        Metodos me = new Metodos();

        pedidosList = me.getAllPedidos(new String[]{""});
        tbPedidos.setItems(pedidosList);
    }

    private void PrepararTabla(){
        tcIdPedido.setCellValueFactory(new PropertyValueFactory<>("id")); tcIdPedido.setResizable(false);
        tcNombUsu.setCellValueFactory(new PropertyValueFactory<>("nombUsu")); tcNombUsu.setResizable(false);
        tcIdAnuncio.setCellValueFactory(new PropertyValueFactory<>("idAnuncio")); tcIdAnuncio.setResizable(false);
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo")); tcTitulo.setResizable(false);
        tcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion")); tcDireccion.setResizable(false);
        tcCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad")); tcCiudad.setResizable(false);
        tcCp.setCellValueFactory(new PropertyValueFactory<>("cp")); tcCp.setResizable(false);
    }
}
