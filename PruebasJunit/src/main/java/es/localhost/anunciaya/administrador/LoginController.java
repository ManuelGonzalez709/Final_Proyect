package es.localhost.anunciaya.administrador;

import es.localhost.anunciaya.administrador.util.DataStore;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de inicio de sesión.
 *
 * @author AnunciaYa
 */
public class LoginController implements Initializable {

    /**
     * Campo de texto para el correo electrónico del usuario.
     */
    @FXML private TextField tfEmail;

    /**
     * Campo de contraseña del usuario.
     */
    @FXML private PasswordField pfPasswd;

    /**
     * Instancia de la clase `Metodos` para realizar operaciones de autenticación y obtención de datos.
     */
    private Metodos m;

    /**
     * Método manejador del evento de clic en el botón de inicio de sesión.
     * Verifica los campos de correo electrónico y contraseña, realiza la autenticación
     * y carga la escena principal si la autenticación es exitosa.
     */
    @FXML
    private void onLoginClick(){
        if(!tfEmail.getText().isEmpty() && !pfPasswd.getText().isEmpty()){
            String[] params = {tfEmail.getText(), pfPasswd.getText()};
            if(m.AuthAdmin(params)){
                // Guarda el ID del usuario en la clase DataStore para usarlo en otras partes de la aplicación
                DataStore.getInstance().setData(m.getIdUserByEmail(tfEmail.getText()));

                // Muestra un diálogo de autenticación exitosa
                Util.mostrarDialogo("MENSAJE_INFO", "Autenticación Exitosa.",
                        """
                                ¡Bienvenido de vuelta al panel de administración!
                                Ahora tienes acceso completo a todas las funciones
                                administrativas.""",
                        Alert.AlertType.INFORMATION);

                // Carga la escena principal (home.fxml) y cambia el título de la ventana
                Util.loadNewScene("home.fxml", tfEmail, "Home | AnunciaYa");
            } else {
                // Muestra un diálogo de autenticación fallida
                Util.mostrarDialogo("MENSAJE_ERROR", "Autenticación Fallida.",
                        "Error al autenticar. Verifica que el correo electrónico y la contraseña sean correctos. ",
                        Alert.AlertType.ERROR);
            }
        } else {
            // Muestra un diálogo indicando que los campos están vacíos
            Util.mostrarDialogo("MENSAJE_WARNING", "Campos Vacíos",
                    "Por favor, completa todos los campos requeridos para iniciar sesión correctamente.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Método de inicialización del controlador.
     * Se ejecuta al cargar la vista (FXML).
     *
     * @param url            URL de inicialización.
     * @param resourceBundle ResourceBundle de inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = new Metodos(); // Inicializa la instancia de Metodos para interactuar con la lógica de negocio
    }
}
