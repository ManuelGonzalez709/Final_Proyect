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

public class LoginController implements Initializable {
    @FXML private TextField tfEmail;
    @FXML private PasswordField pfPasswd;
    private Metodos m;

    @FXML
    private void onLoginClick(){
        if(!tfEmail.getText().isEmpty() && !pfPasswd.getText().isEmpty()){
            String[]params = {tfEmail.getText(),pfPasswd.getText()};
            if(m.AuthAdmin(params)){
                DataStore.getInstance().setData(m.getIdUserByEmail(tfEmail.getText()));
                Util.mostrarDialogo("MENSAJE_INFO", "Autenticación Exitosa.",
                        """
                                ¡Bienvenido de vuelta al panel de administración!
                                Ahora tienes acceso completo a todas las funciones
                                administrativas.""",
                        Alert.AlertType.INFORMATION);

                Util.loadNewScene("home.fxml", tfEmail, "Home | AnunciaYa");
            } else{
                Util.mostrarDialogo("MENSAJE_ERROR", "Autenticación Fallida.",
                        "Error al autenticar. Verifica que el correo electrónico y la contraseña sean correctos. ",
                        Alert.AlertType.ERROR);
            }
        } else{
            Util.mostrarDialogo("MENSAJE_WARNING", "Campos Vacíos",
                    "Por favor, completa todos los campos requeridos para iniciar sesión correctamente.", Alert.AlertType.WARNING);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = new Metodos();
    }
}