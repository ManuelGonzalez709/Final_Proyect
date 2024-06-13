package es.localhost.anunciaya.administrador;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la interfaz de usuario de la página de inicio.
 *
 * @author AnunciaYa
 */
public class Home implements Initializable {

    @FXML private AnchorPane opacityPane, drawerPane, panelFondo;
    @FXML private ImageView drawerImage;
    private int Abierto = 0;

    /**
     * Método de inicialización al cargar la interfaz.
     *
     * @param url URL de inicialización (no utilizada en este contexto).
     * @param resourceBundle ResourceBundle de inicialización (no utilizada en este contexto).
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuLateral();
        cargarFXML("graphics.fxml");
        clickOpacityPannel();
    }

    /**
     * Carga la vista de gráficos en el panel principal.
     */
    public void CargarGraphics() {
        cargarFXML("graphics.fxml");
        clickOpacityPannel();
    }

    /**
     * Carga la vista de usuarios en el panel principal.
     */
    public void CargarUsuarios() {
        cargarFXML("usuarios.fxml");
        clickOpacityPannel();
    }

    /**
     * Carga la vista de anuncios en el panel principal.
     */
    public void CargarAnuncios() {
        cargarFXML("anuncios.fxml");
        clickOpacityPannel();
    }

    /**
     * Carga la vista de categorías en el panel principal.
     */
    public void CargarCategorias() {
        cargarFXML("categorias.fxml");
        clickOpacityPannel();
    }

    /**
     * Carga la vista de pedidos en el panel principal.
     */
    public void CargarPedidos() {
        cargarFXML("pedidos.fxml");
        clickOpacityPannel();
    }

    /**
     * Muestra un cuadro de diálogo de confirmación y cierra la sesión del usuario si se confirma.
     */
    public void CargarLogin() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("Estas a punto de cerrar sesión");
        alert.setContentText("Si confirmas el cierre de sesión deberás volver a iniciar sesión.");

        // Cargar el estilo de la alerta
        String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);

        // Aplicar una clase CSS personalizada al DialogPane
        alert.getDialogPane().getStyleClass().add("custom-alert");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Util.mostrarDialogo("MENSAJE_INFO", "Sesión Cerrada", "Has cerrado sesión exitosamente.", Alert.AlertType.INFORMATION);
            cargarEscenaPrincipal("login.fxml");
        } else {
            Util.mostrarDialogo("MENSAJE_INFO", "Operación cancelada", "La acción de cerrar sesión ha sido cancelada.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Carga un archivo FXML en el panel de fondo.
     *
     * @param NombreView Nombre del archivo FXML a cargar.
     */
    private void cargarFXML(String NombreView) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NombreView));
            AnchorPane content = loader.load();
            panelFondo.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga la escena principal en el Stage actual.
     *
     * @param NombreView Nombre del archivo FXML de la escena principal.
     */
    private void cargarEscenaPrincipal(String NombreView) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NombreView));
            Pane content = loader.load(); // Usar Pane en lugar de AnchorPane para mayor flexibilidad

            // Obtener el stage principal
            Stage stage = (Stage) panelFondo.getScene().getWindow();

            // Crear una nueva escena con el contenido cargado
            Scene newScene = new Scene(content);

            // Establecer la nueva escena en el stage principal
            stage.setScene(newScene);

            // Mostrar el stage principal con la nueva escena
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Error al convertir el layout: " + e.getMessage());
        }
    }

    /**
     * Configura el menú lateral para que se oculte y se muestre al hacer clic.
     */
    private void MenuLateral() {
        opacityPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setByX(-604.5);
        translateTransition.play();

        opacityPane.setOnMouseClicked(event -> clickOpacityPannel());
        drawerImage.setOnMouseClicked(event -> CerrarMenuLateral());
    }

    /**
     * Maneja el evento de clic en el panel de opacidad para cerrar el menú lateral.
     */
    private void clickOpacityPannel() {
        if (Abierto != 0) {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(-604.5);
            translateTransition1.play();
            Abierto = 0;
        }
    }

    /**
     * Abre el menú lateral al hacer clic en la imagen de cajón.
     */
    private void CerrarMenuLateral() {
        if (Abierto != 1) {
            opacityPane.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(+604.5);
            translateTransition1.play();
            Abierto = 1;
        }
    }
}
