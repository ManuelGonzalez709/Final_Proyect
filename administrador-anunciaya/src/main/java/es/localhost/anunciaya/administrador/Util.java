package es.localhost.anunciaya.administrador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase de utilidades para funciones comunes en la aplicación.
 *
 * @author AnunciaYa
 */
public class Util {

    /**
     * Método estático para mostrar un diálogo personalizado.
     *
     * @param titulo      Título del diálogo.
     * @param textoEncab  Texto del encabezado del diálogo.
     * @param contenido   Contenido del diálogo.
     * @param tipoDialogo Tipo del diálogo (INFORMATION, WARNING, ERROR, etc.).
     */
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

    /**
     * Método estático para cargar una nueva escena en la aplicación.
     *
     * @param nScene    Ruta del archivo FXML que define la nueva escena.
     * @param textField Campo de texto utilizado para obtener la escena actual.
     * @param title     Título de la nueva escena.
     */
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
