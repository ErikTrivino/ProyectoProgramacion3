package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.crud.CrudAdminViewController;
import co.edu.uqvirtual.markerplace.crud.CrudVendedorViewController;
import co.edu.uqvirtual.markerplace.exceptions.*;
import co.edu.uqvirtual.markerplace.modelo.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MarketplaceViewController {

    public Tab tabAdministrador;
    public TabPane TabPenaPricipal;
    public Tab tabVendor1;

    public Label lblVendedores_cantidadProductos;
    public Label lblVendedores_cantidadContactos;
    public Label lblProductos_top;
    public Label lblCantidadProductos;
    public Label lblCantidadChats;
    public Label lbNombre1;
    public Label lblApellido1;
    public Label lblCedula1;
    public Tab tabVendedor2;
    public Label lbNombre2;
    public Label lblApellido2;
    public Label lblCedula2;
    public TabPane TabPrincipal;
    public TabPane tabPrincipal;
    public Button btnSalir;
    public Label lbNombre3;
    public Label lblApellido3;
    public Label lblCedula3;
    public Tab tabVendedor3;
    public TableView<Vendedor> tblAliadosVendedor1;
    public Label lblLikes1;

    /**Este es el código del Controller del
     * administrador
     */
    ModelFactoryController modelFactoryController;
    CrudAdminViewController crudAdminViewController;
    CrudVendedorViewController crudVendedorViewController;


    ObservableList<Producto> listadoProductos = FXCollections.observableArrayList();
    ObservableList<Producto> listadoProductos11 = FXCollections.observableArrayList();
    ObservableList<Vendedor> listaVendedorAliado = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Vendedor, String> colNombreAliado1;
    @FXML
    private TableColumn<Vendedor, String> colApellidoAliado1;
    Producto productoSeleccionado;




    private Application aplicacion; // SE DECLARA VARIABLE APLICACION CON GETS AND SETS

    private Usuario usuarioSeleccionado;
    private Vendedor vendedorSeleccionado;//SE DECLARIA UNA VARIABLE DE TIPO VENDEDOR PARA ASIGNAR VENDEDOR SELECCIONADO EN OBSERVABLE LIST
    ObservableList<Vendedor> listadoVendedores = FXCollections.observableArrayList();
    ObservableList<Vendedor> listadoVendedoresEstadisticas = FXCollections.observableArrayList();


    //---------------DECLARACION DE RECURSOS DE VISTA
    @FXML
    private TableView<Producto> tblListaProductosVendedorAliado;
    @FXML
    private TableView<Producto> tblListaProductosVendedorActual;
    @FXML
    private TableColumn<Producto, String> colNombreProducto1;
    @FXML
    private TableColumn<Producto, String> colEstadoProducto1;
    @FXML
    private TableColumn<Producto, String> colPrecioProducto1;
    @FXML
    private TableColumn<Producto, String> colNombreProducto11;
    @FXML
    private TableColumn<Producto, String> colEstadoProducto11;
    @FXML
    private TableColumn<Producto, String> colPrecioProducto11;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Vendedor, String> colCedula;

    @FXML
    private TextField txtAgregarVende_usuario;

    @FXML
    private TableColumn<Vendedor, String> colNombre;
    @FXML
    private TableColumn<Vendedor, String> colApellido;

    @FXML
    private TableView<Vendedor> tblListaVendedores_agregarVendedor;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtAgregarVende_apellido;

    @FXML
    private TextField txtAgregarVende_cedula;

    @FXML
    private TextField txtAgregarVende_contraseña;

    @FXML
    private TextField txtAgregarVende_nombre;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregarProductoVendedor1;

    @FXML
    private TableView<Vendedor> tblVendedoresEstadisticas;

    @FXML
    private TableColumn<Vendedor, String> colNombreEstadisticas;

    boolean logVendedor = false;


    public void determinarTabVendedor() {

        desabilitarTabs();
        int pos = crudVendedorViewController.obtenerPosicionVendedorLog();

        switch (pos) {
            case 0:
                //habilitarTabs();
                tabAdministrador.setDisable(false);
                break;
            case 1:
                logVendedor = true;
                tabVendor1.setDisable(false);
                tabPrincipal.getSelectionModel().select(pos);



                if(crudVendedorViewController.buscarVendedorLog().isPresent()){
                        Vendedor vendedorLoguado =crudVendedorViewController.buscarVendedorLog().get();
                        //modelFactoryController.getMarketPlace().quitarEstadoLogin();

                        modelFactoryController.registrarAccionesSistema("El vendedor "+vendedorLoguado.getNombre()+" esta intentando ingresar al sistema", 1, "Verificar Login");


                        lbNombre1.setText(vendedorLoguado.getNombre());
                        lblApellido1.setText(vendedorLoguado.getApellido());
                        lblCedula1.setText(vendedorLoguado.getCedula());
                    }


                break;
            case 2:
                logVendedor = true;
                tabVendedor2.setDisable(false);
                tabPrincipal.getSelectionModel().select(pos);
                if(crudVendedorViewController.buscarVendedorLog().isPresent()){
                    Vendedor vendedorLoguado = crudVendedorViewController.buscarVendedorLog().get();
                    //modelFactoryController.getMarketPlace().quitarEstadoLogin();

                    modelFactoryController.registrarAccionesSistema("El vendedor "+vendedorLoguado.getNombre()+" esta intentando ingresar al sistema", 1, "Verificar Login");

                    lbNombre2.setText(vendedorLoguado.getNombre());
                    lblApellido2.setText(vendedorLoguado.getApellido());
                    lblCedula2.setText(vendedorLoguado.getCedula());
                }

                break;
                
            case 3:
                logVendedor = true;
                tabVendedor3.setDisable(false);
                tabPrincipal.getSelectionModel().select(pos);
                if(crudVendedorViewController.buscarVendedorLog().isPresent()){
                    Vendedor vendedorLoguado = crudVendedorViewController.buscarVendedorLog().get();
                    //modelFactoryController.getMarketPlace().quitarEstadoLogin();

                    modelFactoryController.registrarAccionesSistema("El vendedor "+vendedorLoguado.getNombre()+" esta intentando ingresar al sistema", 1, "Verificar Login");

                    lbNombre3.setText(vendedorLoguado.getNombre());
                    lblApellido3.setText(vendedorLoguado.getApellido());
                    lblCedula3.setText(vendedorLoguado.getCedula());
                }

                break;
                

            default:
                modelFactoryController.registrarAccionesSistema("Error al cargar datos", 2, "Cargar datos");

                break;

        }
    }

    @FXML
    void btnMeGusta(ActionEvent event) {
        gestionarMeGustaProducto();
    }


    private void gestionarMeGustaProducto() {
        if(productoSeleccionado!=null) {
//            if(){
//
//            }
            modelFactoryController.agregarMegustaProducto(productoSeleccionado);

            lblLikes1.setText(String.valueOf(modelFactoryController.retornarCantidadMeGustas()));

            //System.out.println(productoSeleccionado.getListaMeGustaProducto().get(0).getProducto().getNombre());

        }else {
            modelFactoryController.mostrarMensaje("Notificacion Producto", "Producto no seleccionado", "debe seleccionar un producto el cual le gusta",
                    Alert.AlertType.INFORMATION);
        }

    }


    private void mostraInformacionvendedor(Vendedor vendedor, Usuario usuario) {

        if (vendedor != null) {
            txtAgregarVende_cedula.setDisable(true);
            //txtAgregarVende_cedula.setEditable(false);

            txtAgregarVende_nombre.setText(vendedor.getNombre());
            txtAgregarVende_apellido.setText(vendedor.getApellido());
            txtAgregarVende_cedula.setText(vendedor.getCedula());
            txtAgregarVende_usuario.setText(vendedor.getUsuario().getNombreUsuario());
            txtAgregarVende_contraseña.setText(vendedor.getUsuario().getContrasenia());
        }
    }


    @FXML
    void initialize() {
        this.colNombreProducto1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colEstadoProducto1.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colPrecioProducto1.setCellValueFactory(new PropertyValueFactory<>("precio"));

        this.colNombreProducto11.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colEstadoProducto11.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.colPrecioProducto11.setCellValueFactory(new PropertyValueFactory<>("precio"));

        this.colNombreEstadisticas.setCellValueFactory(new PropertyValueFactory<>("nombre"));


        tblListaProductosVendedorAliado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            //cantidadMegusta.setText(String.valueOf(productoSeleccionado.getListMeGustasProducto().size()));
        });

        tblListaProductosVendedorActual.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            //cantidadMegusta.setText(String.valueOf(productoSeleccionado.getListMeGustasProducto().size()));
        });

        this.colNombreEstadisticas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblVendedoresEstadisticas.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    //vendedorAliadoSeleccionado = newSelection;
                });

        modelFactoryController = ModelFactoryController.getInstance();
        crudAdminViewController = new CrudAdminViewController(modelFactoryController);
        crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);


        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));

        tblListaVendedores_agregarVendedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            vendedorSeleccionado = newSelection;

            mostraInformacionvendedor(vendedorSeleccionado, usuarioSeleccionado);

        });

        determinarTabVendedor();

        cargarListadoProductosAliados();
        cargarListadoProductosActual();
        cargarListadoVendedores();
        cargarListadoVendedoresAliados();
        cargarListadoVendedoresEstadisticas();
        llamarVendedorEstadisticas();


    }

    private void cargarListadoVendedoresAliados() {
        tblAliadosVendedor1.getItems().clear();
        tblAliadosVendedor1.setItems(obtenerVendedoresAliados());
    }
    private ObservableList<Vendedor> obtenerVendedoresAliados() {
        listaVendedorAliado.addAll(modelFactoryController.obtenerVendedorAliado());
        return listaVendedorAliado;
    }

    private ObservableList<Producto> obtenerProductos() {
        listadoProductosAliados.addAll(modelFactoryController.obtenerProductos());
        return listadoProductosAliados;
    }

    private ObservableList<Producto> obtenerProductos11() {
        listadoProductosVendedorActual.addAll(modelFactoryController.obtenerProductosVendedor());
        return listadoProductosVendedorActual;
    }

    private void cargarListadoProductosAliados() {
        tblListaProductosVendedorAliado.getItems().clear();
        tblListaProductosVendedorAliado.setItems(obtenerProductos());
    }

    private void cargarListadoProductosActual() {
        tblListaProductosVendedorActual.getItems().clear();
        tblListaProductosVendedorActual.setItems(obtenerProductos11());
    }

    public void desabilitarTabs() {
        tabAdministrador.setDisable(true);
        tabVendor1.setDisable(true);
        tabVendedor2.setDisable(true);
        tabVendedor3.setDisable(true);
    }


    public void habilitarTabs() {
        tabAdministrador.setDisable(false);
        tabVendor1.setDisable(false);
        tabVendedor2.setDisable(false);
        tabVendedor3.setDisable(false);
    }
    @FXML
    void actualizarVende_agregarVendedorAction(ActionEvent event) throws VendedorNoExisteException, DatosNoIngresadosException, IOException, VendedorException {

        actualizarVendedor();
        txtAgregarVende_cedula.setDisable(false);
        //txtAgregarVende_cedula.setEditable(false);
    }

    private void actualizarVendedor() throws VendedorException {

        String nombre = txtAgregarVende_nombre.getText();
        String apellido = txtAgregarVende_apellido.getText();
        String cedula = txtAgregarVende_cedula.getText();
        String usuario = txtAgregarVende_usuario.getText();
        String contrasenia = txtAgregarVende_contraseña.getText();
        if (vendedorSeleccionado != null) {
            if (datosValidos(nombre, apellido, cedula, usuario, contrasenia)) {

                modelFactoryController.actualizarVendedor(nombre, apellido, cedula, usuario, contrasenia);

                modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor Actualizado", "El Vendedor ha sido actualizado",
                        Alert.AlertType.INFORMATION);

                modelFactoryController.registrarAccionesSistema("El vendedor" + nombre + "actualizado", 1, "Actualizar vendedor");
                cargarListadoVendedores();
                tblListaVendedores_agregarVendedor.refresh();
                limpiarDatos();
            } else {
                try {
                    throw new DatosNulosException("Datos nulos al agregar producto");

                } catch (Exception e) {
                    modelFactoryController.registrarAccionesSistema(e.toString(), 2, "Datos nulos");
                }
            }
        } else {
            modelFactoryController.registrarAccionesSistema("El vendedor no fue seleccionado", 2, "Seleccionar vendedor");
            modelFactoryController.mostrarMensaje("Notificacion vendedor", "vendedor no seleccionado",
                    "n", Alert.AlertType.WARNING);

        }
    }

    @FXML
    void guardarVende_agregarVendedorAction(ActionEvent event) throws DatosNulosException, IOException {
        guardarVendedor();

    }

    private void guardarVendedor() throws DatosNulosException, IOException {
        String nombre = txtAgregarVende_nombre.getText();
        String apellido = txtAgregarVende_apellido.getText();
        String cedula = txtAgregarVende_cedula.getText();
        String usuario = txtAgregarVende_usuario.getText();
        String contracenia = txtAgregarVende_contraseña.getText();

        if (vendedorSeleccionado == null) {
            if (datosValidos(nombre, apellido, cedula, usuario, contracenia)) {

                Vendedor vendedor1 = null;
                vendedor1 = modelFactoryController.crearVendedor(nombre, apellido, cedula, usuario, contracenia);
                cargarListadoVendedores();
                if (vendedor1 != null) {
                    limpiarDatos();
                    modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor registrado",
                            "El Vendedor fue registrado con exito", Alert.AlertType.INFORMATION);
                    modelFactoryController.registrarAccionesSistema("El vendedor fue creado con existo", 1, "Vendedor creado");
                } else {
                    modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no registrado", "El Vendedor no fue registrado",
                            Alert.AlertType.INFORMATION);
                }

            } else {
                try {
                    throw new DatosNulosException("Datos nulos al agregar producto");

                } catch (Exception e) {
                    modelFactoryController.registrarAccionesSistema(e.toString(), 2, "Datos nulos");
                }
            }
        } else {
            modelFactoryController.registrarAccionesSistema("El vendedor no fue registrado", 2, "Crear Vendedor");
            modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no registrado", "no debe seleccionar un vendedor existente",
                    Alert.AlertType.WARNING);
        }
    }
    private void limpiarDatos() {
        txtAgregarVende_nombre.setText("");
        txtAgregarVende_apellido.setText("");
        txtAgregarVende_cedula.setText("");
        txtAgregarVende_usuario.setText("");
        txtAgregarVende_contraseña.setText("");

        lbNombre1.setText("");
        lblApellido1.setText("");
        lblCedula1.setText("");

        lbNombre2.setText("");
        lblApellido2.setText("");
        lblCedula2.setText("");


    }

    @FXML
    void eliminarVende_agregarVendedorAction(ActionEvent event) throws VendedorNoExisteException, IOException, VendedorException {
        eliminarVendedor();
        txtAgregarVende_cedula.setDisable(false);


    }

    private void eliminarVendedor() throws VendedorNoExisteException, IOException, VendedorException {

        if (vendedorSeleccionado != null) {

            if (mostrarMensajeConfirmacion("Esta seguro de eliminar a este Vendedor?")) {

                modelFactoryController.eliminarVendedor(vendedorSeleccionado.getCedula());

                modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor eliminado", "El Vendedor fue eliminado con exito",
                        Alert.AlertType.INFORMATION);
                modelFactoryController.registrarAccionesSistema("El vendedor no fue eliminado", 1, "Vendedor eliminado");
                cargarListadoVendedores();
                tblListaVendedores_agregarVendedor.refresh();
                limpiarDatos();
            } else {
                modelFactoryController.registrarAccionesSistema("El vendedor no fue eliminado", 3, "Eliminar vendedor");
                modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no eliminado", "El Vendedor  no fue eliminado",
                        Alert.AlertType.INFORMATION);
            }

        } else {
            modelFactoryController.registrarAccionesSistema("El vendedor no fue seleccionado", 2, "Seleccionar vendedor");
            modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Vendedor no seleccionado",
                    "Para eliminar un Vendedor debe seleccionar a uno", Alert.AlertType.WARNING);
        }
    }
    private void cargarListadoVendedores() {
        tblListaVendedores_agregarVendedor.getItems().clear();
        tblListaVendedores_agregarVendedor.setItems(obtenerVendedores());
    }

    private void cargarListadoVendedoresEstadisticas() {
        tblVendedoresEstadisticas.getItems().clear();
        tblVendedoresEstadisticas.setItems(obtenerVendedoresEstadisticas());
    }

    private ObservableList<Vendedor> obtenerVendedores() {
        listadoVendedores.addAll(modelFactoryController.obtenerVendedores());
        return listadoVendedores;
    }

    private ObservableList<Vendedor> obtenerVendedoresEstadisticas() {
        listadoVendedoresEstadisticas.addAll(modelFactoryController.obtenerVendedores());
        return listadoVendedoresEstadisticas;
    }

    private boolean datosValidos(String nombre, String apellido, String cedula, String usuario,
                                 String contrasenia) {

        String mensaje = "";

        if (nombre == null || nombre.equals("") || nombre.isEmpty()) {
            mensaje += "El nombre es invalido";
        }
        if (apellido == null || apellido.equals("") || apellido.isEmpty()) {
            mensaje += "El apellido es invalido";
        }
        if (cedula == null || cedula.equals("") || cedula.isEmpty()) {
            mensaje += "El documento es invalido";
        }
        if (usuario == null || usuario.equals("") || usuario.isEmpty()) {
            mensaje += "el usuario es invalido";
        }
        if (contrasenia == null || contrasenia.equals("") || contrasenia.isEmpty()) {
            mensaje += "la contrasenia es invalido";
        }
        try {
            if (modelFactoryController.verificarVendedorExistente(cedula) && vendedorSeleccionado == null) {
                mensaje += "Ya existe un vendedor con  documento";
            }
        } catch (Exception e) {
            modelFactoryController.registrarAccionesSistema(e.getMessage(), 2, "Insertar datos");
        }


        if (contrasenia.length() > 5) {
            mensaje += "la contrasenia debe tener 5 caracteres o menos";
        }
        if (mensaje.equals("")) {
            return true;
        }

        modelFactoryController.registrarAccionesSistema("Datos invalidos", 2, "Insertar datos");
        modelFactoryController.mostrarMensaje("Notificacion Vendedor", "Datos Invalidos", mensaje, Alert.AlertType.WARNING);

        return false;
    }
    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmacion");
        alerta.setContentText(mensaje);
        Optional<ButtonType> action = alerta.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    /**
     * Código del tab de
     * vendedor 1
     */

    @FXML
    void agregarProducto_vendedor1ViewAction(ActionEvent actionEvent) throws IOException, VendedorNoExisteException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/agregarProductoView.fxml"));


        Parent root = loader.load();
        AgregarProductoViewController agregarProductoViewController = loader.getController();
        if (lbNombre1.getText() != null) {

        }
        agregarProductoViewController.recibirNombre(lbNombre1.getText());


        //root.setUserData(lbNombre1.getText());

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        //AgregarProductoViewController inicioForm = loader.getController();
        //inicioForm.init(lbNombre1.getText(), stage, this);

    }

    @FXML
    void comentarios_vendedor1ViewAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/comentariosView.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void gestionarAliados_vendedor1ViewAction(ActionEvent actionEvent) throws IOException, VendedorNoExisteException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/gestionarAliados.fxml"));

        Parent root = loader.load();
        /*
        GestionarAliadosControllerViewController gestionarAliadosControllerViewController = loader.getController();
        if(lbNombre1.getText() != null){

        }
        gestionarAliadosControllerViewController.recibirNombre(lbNombre1.getText());
        */

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void iniciarCharla_vendedor1ViewAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/chatView.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void verProductosAliados_vendedir1ViewAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/verProductosAliadoView.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }


    public void mostrarDatosVendedor() {

    }

    public void salir(ActionEvent actionEvent) {

        if (logVendedor) {
            modelFactoryController.getMarketPlace().quitarEstadoLogin();
            logVendedor = false;
        } else {
            crudAdminViewController.cambiarEstadoLoginAdmin();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/loginView.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
