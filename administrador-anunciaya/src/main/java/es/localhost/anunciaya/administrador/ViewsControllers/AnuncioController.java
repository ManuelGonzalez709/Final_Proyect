package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Anuncio;
import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AnuncioController implements Initializable {
    private ObservableList<Anuncio> anunciosList;
    private List<Image> images = new ArrayList<>();

    @FXML private TableView<Anuncio> tbAnuncios;
    @FXML private TableColumn<Anuncio, Integer> tcIdAnuncio;
    @FXML private TableColumn<Anuncio, String> tcNombUsu;
    @FXML private TableColumn<Anuncio, String> tcCategoria;
    @FXML private TableColumn<Anuncio, String> tcTitulo;
    @FXML private TableColumn<Anuncio, String> tcDescripcion;
    @FXML private TableColumn<Anuncio, String> tcEstado;
    @FXML private TableColumn<Anuncio, String> tcUbicacion;
    @FXML private TableColumn<Anuncio, Double> tcPrecio;
    @FXML private TableColumn<Anuncio, String> tcFotos;
    @FXML private TableColumn<Anuncio, String> tcFechPubl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrepararTabla();
        CargarDatosTabla();
    }

    private void CargarDatosTabla() {
        Metodos me = new Metodos();
        anunciosList = me.getAllAnuncios(new String[]{""});
        tbAnuncios.setItems(anunciosList);
    }

    private void PrepararTabla() {
        tcIdAnuncio.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNombUsu.setCellValueFactory(new PropertyValueFactory<>("nombUsu"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tcUbicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcFotos.setCellValueFactory(new PropertyValueFactory<>("fotos"));
        tcFechPubl.setCellValueFactory(new PropertyValueFactory<>("fechPubl"));
    }

    @FXML
    private void onVerRegClick() {
        Anuncio selectedAnuncio = tbAnuncios.getSelectionModel().getSelectedItem();
        if (selectedAnuncio != null) {
            List<String> urls = obtenerUrlsImagenes(selectedAnuncio.getFotos());

            // Cargar imágenes desde las URLs
            images.clear();
            for (String url : urls) {
                images.add(new Image(url));
            }
            showImageCarouselDialog();
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "No se seleccionó ningún anuncio", "Por favor, selecciona un anuncio para poder visualizar sus fotos.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBorrarRegClick() {
        Metodos m = new Metodos();
        Anuncio selectedAnuncio = tbAnuncios.getSelectionModel().getSelectedItem();

        if (selectedAnuncio != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar el anuncio?");
            alert.setContentText("Esta acción no se puede deshacer.");

            // Cargar la hoja de estilo
            String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            // Aplicar una clase CSS personalizada al DialogPane
            alert.getDialogPane().getStyleClass().add("custom-alert");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (m.deleteAnuncio(new String[]{String.valueOf(selectedAnuncio.getId())})) {
                    tbAnuncios.getItems().remove(selectedAnuncio);
                    tbAnuncios.refresh();
                    Util.mostrarDialogo("MENSAJE_INFO", "Anuncio eliminado", "El anuncio ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
                } else {
                    Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo eliminar el anuncio", "El anuncio no pudo ser eliminado del servidor.", Alert.AlertType.ERROR);
                }
            } else {
                Util.mostrarDialogo("MENSAJE_INFO", "Eliminación cancelada", "La eliminación del anuncio ha sido cancelada.", Alert.AlertType.INFORMATION);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "No se seleccionó ningún anuncio", "Por favor, selecciona un anuncio para eliminar.", Alert.AlertType.WARNING);
        }
    }

    private List<String> obtenerUrlsImagenes(String cadenaUrls) {
        List<String> urls = new ArrayList<>();
        if (cadenaUrls != null && !cadenaUrls.isEmpty()) {
            String[] partes = cadenaUrls.split(";");
            for (String parte : partes) {
                urls.add(parte.trim());
            }
        }
        return urls;
    }

    private void showImageCarouselDialog() {
        try {
            Anuncio selectedAnuncio = tbAnuncios.getSelectionModel().getSelectedItem();
            // Cargar el archivo FXML desde los recursos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/localhost/anunciaya/administrador/imagenes_anuncio.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del FXML y configurar las imágenes
            CarrouselController controller = loader.getController();
            controller.setImages(images);

            // Crear y configurar el Stage para el diálogo modal
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Imágenes Anuncio " + selectedAnuncio.getId());
            
            DialogPane dialogPane = new DialogPane();
            dialogPane.setContent(root);
            stage.setHeight(430);
            stage.setScene(new Scene(dialogPane));
            stage.setResizable(false);

            // Establecer la ventana principal como propietario de la ventana emergente
            Stage primaryStage = (Stage) tbAnuncios.getScene().getWindow(); // Reemplaza 'tbAnuncios' con cualquier nodo de tu ventana principal
            stage.initOwner(primaryStage);

            // Mostrar la ventana emergente y esperar hasta que se cierre
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
