package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la vista de actualización de la contraseña de un usuario.
 *
 * @author AnunciaYa
 */
public class NuevaPassController {

    /**
     * Controlador de la vista principal de usuarios, usado para refrescar la tabla de usuarios
     * después de actualizar la contraseña.
     */
    private UserController userController;

    /**
     * Campo de texto para ingresar la nueva contraseña del usuario.
     */
    @FXML
    private TextField tfContraseña;

    /**
     * Botón para cancelar la operación de actualización de la contraseña y cerrar la ventana.
     */
    @FXML
    private Button btCancelar;

    /**
     * Identificador del usuario cuya contraseña se está actualizando.
     */
    private int IdUsuario;

    /**
     * Establece el controlador principal de usuarios y el identificador del usuario cuya contraseña
     * se va a actualizar. Este método enlaza este controlador con el controlador de la vista principal.
     *
     * @param UserControler el controlador de la vista principal de usuarios.
     * @param IdUsuario el identificador del usuario.
     */
    public void setPassController(UserController UserControler, int IdUsuario) {
        this.userController = UserControler;
        this.IdUsuario = IdUsuario;
    }

    /**
     * Maneja el evento de clic en el botón "Aceptar" para actualizar la contraseña del usuario.
     * Verifica que el campo de contraseña no esté vacío, intenta actualizar la contraseña,
     * y muestra mensajes de éxito o error según el resultado.
     */
    @FXML
    private void onAceptarClick() {
        Metodos m = new Metodos();

        // Verifica que el campo de contraseña no esté vacío
        if (!tfContraseña.getText().isEmpty()) {
            // Intenta actualizar la contraseña en la base de datos
            if (m.updateContras(new String[]{Integer.toString(IdUsuario), tfContraseña.getText()})) {
                // Actualización exitosa: muestra un mensaje y cierra la ventana
                Util.mostrarDialogo("MENSAJE_INFO", "Contraseña actualizada",
                        "Se ha cambiado la contraseña correctamente", Alert.AlertType.INFORMATION);
                userController.CargarDatosTabla();
                Cerrar();
            } else {
                Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo actualizar la contraseña",
                        "Ha surgido un error con la actualización de la contraseña", Alert.AlertType.ERROR);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "Hay campos vacíos",
                    "Por favor, comprueba que todos los campos están completos antes de actualizar la contraseña.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Maneja el evento de clic en el botón "Cancelar" para cerrar la ventana.
     * Llama al método {@link #Cerrar()} para cerrar la ventana y refrescar la tabla de usuarios.
     */
    public void btCancelar() {
        Cerrar();
    }

    /**
     * Cierra la ventana actual y refresca la tabla de usuarios en la vista principal.
     */
    private void Cerrar() {
        // Refresca la tabla de usuarios en el controlador principal
        userController.CargarDatosTabla();

        // Obtiene el Stage actual a partir del botón "Cancelar" y lo cierra
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}
