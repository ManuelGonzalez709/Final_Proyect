package es.localhost.anunciaya.administrador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Clase principal de la aplicación que inicia la interfaz de usuario.
 * Extiende de la clase `Application` de JavaFX y representa el punto de entrada
 * para la aplicación AnunciaYa. Esta clase carga el archivo FXML del login,
 * configura la escena y muestra la ventana de inicio de sesión.
 *
 * @author AnunciaYa
 */
public class HelloApplication extends Application {

    /**
     * Método principal que configura y muestra la ventana principal de la aplicación.
     * Carga el archivo FXML del login, configura la escena y muestra la ventana
     * con un tamaño fijo y título específico.
     *
     * @param stage El objeto Stage proporcionado por JavaFX para configurar la ventana principal.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 558, 406);
        stage.setTitle("Login | AnunciaYa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal de inicio de la aplicación.
     * Llama al método `launch()` de JavaFX para iniciar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación).
     */
    public static void main(String[] args) {
        launch();
    }
}
