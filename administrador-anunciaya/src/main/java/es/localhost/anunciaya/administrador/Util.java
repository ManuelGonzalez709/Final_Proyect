package es.localhost.anunciaya.administrador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Util {
    public static void mostrarDialogo(String titulo, String textoEncab, String contenido, Alert.AlertType tipoDialogo) {
        Alert alert = new Alert(tipoDialogo);
        alert.setTitle(titulo);
        alert.setHeaderText(textoEncab);
        alert.setContentText(contenido);

        // Cargar la hoja de estilo
        String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);

        // Aplicar una clase CSS personalizada al DialogPane
        alert.getDialogPane().getStyleClass().add("custom-alert");

        alert.showAndWait();
    }

    public static void loadNewScene(String nScene, TextField textField, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Util.class.getResource(nScene));
            Parent root = loader.load();

            // Obtiene la escena actual
            Stage stage = (Stage) textField.getScene().getWindow();
            stage.setTitle(title);

            Scene scene = new Scene(root);

            // Asigna la Stage a la escena
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
