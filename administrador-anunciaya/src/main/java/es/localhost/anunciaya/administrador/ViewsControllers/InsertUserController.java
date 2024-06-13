package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de inserción de un nuevo usuario.
 * Implementa {@link Initializable} para manejar la inicialización de componentes FXML.
 *
 * @author AnunciaYa
 */
public class InsertUserController implements Initializable {

    /**
     * Botón para cancelar la operación de inserción y cerrar la ventana.
     */
    @FXML
    private Button btCancelar;

    /**
     * Campo de texto para ingresar el nombre del usuario.
     */
    @FXML
    private TextField Nombre;

    /**
     * Campo de texto para ingresar los apellidos del usuario.
     */
    @FXML
    private TextField Apellidos;

    /**
     * Campo de texto para ingresar el nombre de usuario.
     */
    @FXML
    private TextField NombreUsuario;

    /**
     * Campo de texto para ingresar el email del usuario.
     */
    @FXML
    private TextField Email;

    /**
     * Campo de texto para ingresar el teléfono del usuario.
     */
    @FXML
    private TextField Telefono;

    /**
     * Selector de fecha para la fecha de nacimiento del usuario.
     */
    @FXML
    private DatePicker dateFechaNacimiento;

    /**
     * Botón de menú dividido para seleccionar el tipo de usuario.
     */
    @FXML
    private SplitMenuButton slTipo;

    /**
     * Campo de texto para ingresar la contraseña del usuario.
     */
    @FXML
    private PasswordField tbContrasena;

    /**
     * Instancia de la clase {@link Metodos} para manejar las operaciones relacionadas con los datos.
     */
    private Metodos m;

    /**
     * Controlador de la vista principal de usuarios, utilizado para refrescar la tabla de usuarios
     * después de la inserción de un nuevo usuario.
     */
    private UserController userController;

    /**
     * Establece el controlador principal de usuarios.
     * Este método se utiliza para enlazar este controlador con el controlador de la vista principal.
     *
     * @param userController el controlador de la vista principal de usuarios.
     */
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    /**
     * Método de inicialización para configurar componentes o preparar el controlador.
     * Este método se llama automáticamente al inicializar la vista.
     *
     * @param url no se utiliza en este contexto.
     * @param resourceBundle no se utiliza en este contexto.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crear una nueva instancia de la clase Metodos
        m = new Metodos();
    }

    /**
     * Maneja el evento de clic en el botón "Aceptar" para añadir un nuevo usuario.
     * Verifica que todos los campos requeridos estén completos, intenta insertar el usuario,
     * y muestra mensajes de éxito o error según el resultado.
     */
    public void btAceptar() {
        try {
            // Verifica que los campos requeridos no estén vacíos
            if (!Nombre.getText().isEmpty() && !Apellidos.getText().isEmpty() && !NombreUsuario.getText().isEmpty()) {
                if (!Email.getText().isEmpty() && !Telefono.getText().isEmpty() && !tbContrasena.getText().isEmpty()) {
                    try {
                        // Verifica que el teléfono sea un número válido
                        Integer.parseInt(Telefono.getText());

                        // Formatea la fecha de nacimiento
                        String fechaNacimiento = dateFechaNacimiento.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        // Prepara los parámetros para la inserción del usuario
                        String[] params = {
                                Nombre.getText(),
                                Apellidos.getText(),
                                NombreUsuario.getText(),
                                tbContrasena.getText(),
                                fechaNacimiento,
                                Email.getText(),
                                Telefono.getText(),
                                slTipo.getText()
                        };

                        // Intenta insertar el nuevo usuario en la base de datos
                        if (m.insertarUsuario(params)) {
                            // Inserción exitosa: muestra un mensaje y cierra la ventana
                            Util.mostrarDialogo("Mensaje del Server", "Insercción de Usuario",
                                    "Usuario Insertado Correctamente", Alert.AlertType.INFORMATION);
                            Cerrar();
                        } else {
                            // Error en la inserción: muestra un mensaje de error
                            Util.mostrarDialogo("Mensaje del Server", "Insercción de Usuario",
                                    "El Usuario no pudo ser Insertado", Alert.AlertType.INFORMATION);
                        }
                    } catch (Exception e) {
                        // Error de formato de teléfono u otro error: muestra un mensaje de error
                        Util.mostrarDialogo("Teléfono Erróneo", "Número de Tf Erróneo",
                                "Número de Teléfono Erróneo u otro error: " + e, Alert.AlertType.INFORMATION);
                    }
                } else {
                    // Campos incompletos: muestra un mensaje de advertencia
                    Util.mostrarDialogo("Campos Erróneos", "Campos Incompletos",
                            "Rellena los campos correctamente", Alert.AlertType.INFORMATION);
                }
            } else {
                // Campos incompletos: muestra un mensaje de advertencia
                Util.mostrarDialogo("Campos Erróneos", "Campos Incompletos",
                        "Rellena los campos correctamente", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            // Error del servidor: muestra un mensaje de error
            Util.mostrarDialogo("Server_Error", "Autenticación Fallida.",
                    "Ocurrió un error en el servidor: " + e, Alert.AlertType.INFORMATION);
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

    /**
     * Establece el texto del {@link SplitMenuButton} según el menú seleccionado.
     *
     * @param event el evento de selección en el menú.
     */
    public void EstablecerTextoSelector(javafx.event.ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        slTipo.setText(menuItem.getText());
    }
}
