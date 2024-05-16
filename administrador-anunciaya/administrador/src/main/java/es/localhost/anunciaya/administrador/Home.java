package es.localhost.anunciaya.administrador;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @FXML
    private AnchorPane opacityPane,drawerPane,panelFondo;

    @FXML
    private ImageView drawerImage;
    private int Abierto = 0 ;

    @FXML
    private ImageView exit;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuLateral();
        cargarFXML("graphics.fxml");// cargamos el grafico como pagina principal
    }
    public void CargarGraphics(){
        cargarFXML("graphics.fxml");
    }
    public void CargarUsuarios(){
        cargarFXML("usuarios.fxml");
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
    private void MenuLateral(){
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

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
