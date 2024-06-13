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

public class Home implements Initializable {
    @FXML private AnchorPane opacityPane,drawerPane,panelFondo;
    @FXML private ImageView drawerImage;
    private int Abierto = 0 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuLateral();
        cargarFXML("graphics.fxml");clickOpacityPannel();
    }
    public void CargarGraphics(){
        cargarFXML("graphics.fxml");clickOpacityPannel();
    }
    public void CargarUsuarios(){
        cargarFXML("usuarios.fxml");clickOpacityPannel();
    }
    public void CargarAnuncios(){
        cargarFXML("anuncios.fxml");clickOpacityPannel();}
    public void CargarCategorias(){
        cargarFXML("categorias.fxml");clickOpacityPannel();}
    public void CargarPedidos(){
        cargarFXML("pedidos.fxml");clickOpacityPannel();}
    public void CargarLogin(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("Estas apunto de cerrar sesión");
        alert.setContentText("Si confirmas el cerrar sesión deberás de volver a iniciar la sesión.");

        // Cargar la hoja de estilo
        String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);

        // Aplicar una clase CSS personalizada al DialogPane
        alert.getDialogPane().getStyleClass().add("custom-alert");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Util.mostrarDialogo("MENSAJE_INFO", "Sesión Cerrada", "Has cerrado sesión exitosamente.", Alert.AlertType.INFORMATION);
            cargarEscenaPrincipal("login.fxml");
        } else{
            Util.mostrarDialogo("MENSAJE_INFO", "Operación cancelada", "La acción de cerrar sesión ha sido cancelada.", Alert.AlertType.INFORMATION);
        }
    }


    private void cargarFXML(String NombreView){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(NombreView));
            AnchorPane content = loader.load();
            panelFondo.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void MenuLateral(){
        opacityPane.setVisible(false);

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(0.5),opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),drawerPane);
        translateTransition.setByX(-604.5);
        translateTransition.play();

        opacityPane.setOnMouseClicked(event -> clickOpacityPannel());
        drawerImage.setOnMouseClicked(event -> CerrarMenuLateral());

    }
    private void clickOpacityPannel(){
        if(Abierto != 0){
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });


            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(-604.5);
            translateTransition1.play();
            Abierto = 0;
        }
    }
    private void CerrarMenuLateral(){
        if(Abierto != 1){
            opacityPane.setVisible(true);

            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),drawerPane);
            translateTransition1.setByX(+604.5);
            translateTransition1.play();
            Abierto = 1;
        }
    }
}
