package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.User;
import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.DataStore;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de la tabla de usuarios. Maneja la lógica para
 * cargar, mostrar, insertar, actualizar y eliminar usuarios en una tabla.
 *
 * @author AnunciaYa
 */
public class UserController implements Initializable {

    /**
     * Tabla que muestra la lista de usuarios.
     */
    @FXML
    private TableView<User> tbUsuarios;

    /**
     * Columna que muestra el identificador del usuario.
     */
    @FXML
    private TableColumn<User, Integer> idColumn;

    /**
     * Columna que muestra el nombre del usuario.
     */
    @FXML
    private TableColumn<User, String> nombreColumn;

    /**
     * Columna que muestra los apellidos del usuario.
     */
    @FXML
    private TableColumn<User, String> apellidosColumn;

    /**
     * Columna que muestra el nombre de usuario.
     */
    @FXML
    private TableColumn<User, String> nombUsuColumn;

    /**
     * Columna que muestra la fecha de nacimiento del usuario.
     */
    @FXML
    private TableColumn<User, String> fechaNacimientoColumn;

    /**
     * Columna que muestra el correo electrónico del usuario.
     */
    @FXML
    private TableColumn<User, String> emailColumn;

    /**
     * Columna que muestra el teléfono del usuario.
     */
    @FXML
    private TableColumn<User, String> telefonoColumn;

    /**
     * Columna que muestra el tipo de usuario.
     */
    @FXML
    private TableColumn<User, String> tipoColumn;

    /**
     * Instancia de la clase {@link Metodos} que maneja la lógica de negocio.
     */
    private Metodos me;

    /**
     * Identificador del usuario actual.
     */
    private String IdUsuario;

    /**
     * Lista observable de usuarios para llenar la tabla.
     */
    private ObservableList<User> userList;

    /**
     * Inicializa el controlador después de cargar el archivo FXML.
     * Este método se llama automáticamente cuando se carga la vista.
     *
     * @param url URL de la ubicación utilizada para resolver rutas relativas del objeto raíz o nulo si no se conoce.
     * @param resourceBundle El recurso utilizado para localizar el objeto raíz o nulo si no se localiza.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IdUsuario = DataStore.getInstance().getData();
        me = new Metodos();
        PrepararTabla();
        CargarDatosTabla();
    }

    /**
     * Carga los datos de usuarios en la tabla. Utiliza la clase {@link Metodos} para obtener
     * todos los usuarios y los asigna a la tabla.
     */
    public void CargarDatosTabla() {
        String[] params = {IdUsuario};
        userList = me.getAllUsers(params);
        tbUsuarios.setItems(userList);
    }

    /**
     * Prepara la tabla de usuarios configurando las columnas y sus respectivos
     * atributos. Configura cada columna para que muestre los datos correspondientes
     * de la clase {@link User}.
     */
    private void PrepararTabla() {
        // Configura las columnas de la tabla para que correspondan a los atributos de User
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        idColumn.setResizable(false);

        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        nombreColumn.setResizable(false);

        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        apellidosColumn.setResizable(false);

        nombUsuColumn.setCellValueFactory(new PropertyValueFactory<>("nomb_usu"));
        nombUsuColumn.setResizable(false);

        fechaNacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimiento"));
        fechaNacimientoColumn.setResizable(false);

        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        emailColumn.setResizable(false);

        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        telefonoColumn.setResizable(false);

        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tipoColumn.setResizable(false);

        // Configurar la tabla y las columnas como editables
        tbUsuarios.setEditable(true);
        fechaNacimientoColumn.setEditable(false);
        tipoColumn.setEditable(false);

        // Configurar CellFactory para permitir edición
        nombreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        apellidosColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombUsuColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Agregar manejadores de commit para actualizar el modelo de datos
        nombreColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setNombre(event.getNewValue());
            Actualizar(user);
        });
        apellidosColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setApellidos(event.getNewValue());
            Actualizar(user);
        });
        nombUsuColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setNomb_usu(event.getNewValue());
            Actualizar(user);
        });
        emailColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setEmail(event.getNewValue());
            Actualizar(user);
        });
        telefonoColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setTelefono(event.getNewValue());
            Actualizar(user);
        });
    }

    /**
     * Abre la ventana para insertar un nuevo usuario. Utiliza un archivo FXML para
     * la vista de inserción.
     */
    public void btInsertar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/localhost/anunciaya/administrador/insert_usuario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Configurar el controlador de inserción de usuario
            InsertUserController insertUserController = loader.getController();
            insertUserController.setUserController(this);

            // Crear y configurar el Stage para el diálogo modal
            Stage currentStage = (Stage) tbUsuarios.getScene().getWindow();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Insertar Usuario");
            dialogStage.setScene(scene);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(currentStage);
            dialogStage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Elimina el usuario seleccionado en la tabla. Muestra una confirmación antes de proceder.
     */
    public void btBorrar() {
        User selectedUser = tbUsuarios.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Alert de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar el usuario?");
            alert.setContentText("Esta acción no se puede deshacer.");

            // Cargar la hoja de estilo
            String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            // Aplicar una clase CSS personalizada al DialogPane
            alert.getDialogPane().getStyleClass().add("custom-alert");

            Optional<ButtonType> result = alert.showAndWait();

            // Si se pulsó en OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    String[] params = {Integer.toString(tbUsuarios.getSelectionModel().getSelectedItem().getId())};
                    if (me.borrarUsuario(params)) {
                        Util.mostrarDialogo("Server Message", "Borrado de Usuario", "El usuario con id: " + params[0] + ", ha sido borrado correctamente", Alert.AlertType.INFORMATION);
                    } else {
                        Util.mostrarDialogo("Server Message", "Borrado de Usuario", "El borrado no se pudo llevar a cabo :(", Alert.AlertType.INFORMATION);
                    }
                    CargarDatosTabla();
                } catch (Exception e) {
                    Util.mostrarDialogo("App Message", "Borrado de Usuario", "Error en el Borrado " + e, Alert.AlertType.INFORMATION);
                }
            } else {
                Util.mostrarDialogo("MENSAJE_INFO", "Eliminación cancelada", "La eliminación del usuario ha sido cancelado.", Alert.AlertType.INFORMATION);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "No se seleccionó ningun usuario", "Por favor, selecciona un usuario para eliminar.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Abre la ventana para cambiar la contraseña del usuario seleccionado. Utiliza un archivo FXML para
     * la vista de modificación de contraseña.
     */
    @FXML
    private void btCambiarPass() {
        User selectedUser = tbUsuarios.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            try {
                // Cargar el archivo FXML desde los recursos
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/localhost/anunciaya/administrador/nueva_contras.fxml"));
                Parent root = loader.load();

                // Obtener el controlador del FXML y configurar las imágenes
                NuevaPassController controller = loader.getController();
                controller.setPassController(this, selectedUser.getId());

                // Crear y configurar el Stage para el diálogo modal
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modificar contraseña");
                stage.setScene(new Scene(root));
                stage.setResizable(false);

                // Establecer la ventana principal como propietario de la ventana emergente
                Stage primaryStage = (Stage) tbUsuarios.getScene().getWindow();
                stage.initOwner(primaryStage);

                // Mostrar la ventana emergente y esperar hasta que se cierre
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "No se seleccionó ningun usuario", "Por favor, selecciona un usuario para cambiar su contraseña.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Actualiza la información del usuario en la base de datos.
     *
     * @param usuario El usuario a actualizar.
     */
    public void Actualizar(User usuario) {
        try {
            String[] params = {
                    Integer.toString(usuario.getId()),
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getTelefono(),
                    usuario.getEmail(),
                    usuario.getFecha_nacimiento(),
                    usuario.getNomb_usu()
            };
            if (!me.actualizarUsuario(params)) {
                Util.mostrarDialogo("App Message", "Actualizado de Usuario", "Error en el Actualizado ", Alert.AlertType.INFORMATION);
            }
            CargarDatosTabla();
        } catch (Exception e) {
            Util.mostrarDialogo("App Message", "Actualizado de Usuario", "Error en el Actualizado " + e, Alert.AlertType.INFORMATION);
        }
    }
}
