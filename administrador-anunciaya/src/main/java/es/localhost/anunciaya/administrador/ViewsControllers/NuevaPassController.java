package es.localhost.anunciaya.administrador.ViewsControllers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevaPassController {
    private UserController userController;
    @FXML
    private TextField tfContraseña;
    @FXML private Button btCancelar;
    private int IdUsuario;

    public void setPassController(UserController UserControler,int IdUsuario) {
        this.userController = UserControler;this.IdUsuario = IdUsuario;
    }

    @FXML private void onAceptarClick() {
        Metodos m = new Metodos();
        if (!tfContraseña.getText().isEmpty()) {
            if(m.updateContras(new String[]{Integer.toString(IdUsuario),tfContraseña.getText()})){
                Util.mostrarDialogo("MENSAJE_INFO", "Contraseña actualizada", "Se ha cambiado la contraseña correctamente", Alert.AlertType.INFORMATION);
                userController.CargarDatosTabla();
                Cerrar();
            } else{
                Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo actualizar la contraseña", "Ha surgido un error con la actualización de la contraseña", Alert.AlertType.ERROR);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "Hay campos vacíos", "Por favor comprueba que todos los campos están completos antes de actualizar la contraseña.", Alert.AlertType.WARNING);
        }
    }

    public void btCancelar(){Cerrar();}
    private void Cerrar(){
        userController.CargarDatosTabla();
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}
