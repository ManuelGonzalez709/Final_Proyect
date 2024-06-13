package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.awt.SystemColor.window;

public class InsertUserController implements Initializable {
    @FXML private Button btCancelar;
    @FXML TextField Nombre,Apellidos,NombreUsuario,Email,Telefono;
    @FXML private DatePicker dateFechaNacimiento;
    @FXML private SplitMenuButton slTipo;
    @FXML private PasswordField tbContrasena;
    private Metodos m ;
    private UserController userController;

    // Método para establecer la referencia del controlador padre
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = new Metodos();
    }
    public void btAceptar(){
        try{
            if(!Nombre.getText().isEmpty() && !Apellidos.getText().isEmpty() && !NombreUsuario.getText().isEmpty()){
                if(!Email.getText().isEmpty() && !Telefono.getText().isEmpty() && !tbContrasena.getText().isEmpty()){
                    try{
                        Integer.parseInt(Telefono.getText().toString());
                        String fechaNacimiento = dateFechaNacimiento.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        String []params = {Nombre.getText(),Apellidos.getText(),NombreUsuario.getText(),tbContrasena.getText(),fechaNacimiento,Email.getText(),Telefono.getText(),slTipo.getText()};
                        if(m.insertarUsuario(params)){
                            Util.mostrarDialogo("Mensaje del Server", "Inserccion de Usuario","Usuario Insertado Correctamente", Alert.AlertType.INFORMATION);
                            Cerrar();
                        }else Util.mostrarDialogo("Mensaje del Server", "Inserccion de Usuario","El Usuario no pudo ser Insertado", Alert.AlertType.INFORMATION);
                    }catch (Exception e){Util.mostrarDialogo("Telefono Erroneo", "Numero de Tf Erroneo","Numero de Telefono Erroneo u otro error: "+e, Alert.AlertType.INFORMATION);}
                }else Util.mostrarDialogo("Campos Erróneos", "Campos Incompletos","Rellena los campos correctamente", Alert.AlertType.INFORMATION);
            }else Util.mostrarDialogo("Campos Erróneos", "Campos Incompletos","Rellena los campos correctamente", Alert.AlertType.INFORMATION);
        }catch (Exception e) {Util.mostrarDialogo("Server_Error", "Autenticación Fallida.","Ocurrio un error en el servidor: "+e, Alert.AlertType.INFORMATION);}
    }
    public void btCancelar(){Cerrar();}
    private void Cerrar(){
        userController.CargarDatosTabla();
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();

    }
    public void EstablecerTextoSelector(javafx.event.ActionEvent event){
        MenuItem menuItem = (MenuItem) event.getSource();
        slTipo.setText(menuItem.getText());
    }
}
