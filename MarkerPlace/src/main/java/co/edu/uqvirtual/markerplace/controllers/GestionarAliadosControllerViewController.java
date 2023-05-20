package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.Application;
import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.modelo.Estado;
import co.edu.uqvirtual.markerplace.modelo.Solicitud;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
public class GestionarAliadosControllerViewController {
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    private Application application;
    private ResourceBundle resources;
    private URL location;

    //---------------DECLARACION DE RECURSOS DE VISTA
    //Tablas
    @FXML
    private TableView<Solicitud> tblSolicitudesAmistad_gestionarAliados;
    @FXML
    private TableColumn<Vendedor,String> colNombreSolicitud;
    @FXML
    private TableColumn<Vendedor, String> colApellidoSolicitud;

    //Botones
    @FXML
    private Button btnRefrescarTbl_gestionarAliados;
    @FXML
    private Button btnBuscar_gestionarAliados;
    @FXML
    private Button btnAceptar_gestionarAliados;
    @FXML
    private Button btnRechazar_GestionarAliados;
    @FXML
    private Button btnEnviarSugeren_gestionarAliados;
    @FXML
    private Button btnSalir_gestionarAliadosView;

    //-------Instancias de los vendedores--------
    Vendedor vendedor;
    Solicitud solicitudSelecionada;

    ArrayList<Vendedor> vendedoresMarketPlace = modelFactoryController.getMarketPlace().getListVendedores();

    //-------Obvervables
    ObservableList<Solicitud> listaSolicitudes = FXCollections.observableArrayList();
    ObservableList<Vendedor> listadoVendedoresSugeridos = FXCollections.observableArrayList();

    @FXML
    void initialize() throws IOException {
        this.colNombreSolicitud.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colApellidoSolicitud.setCellValueFactory(new PropertyValueFactory<>("cedula"));

        tblSolicitudesAmistad_gestionarAliados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            solicitudSelecionada = newSelection;

        });
        vendedor = modelFactoryController.getVendedorAutenticado();
        cargarListaSolicitudes();
    }
    @FXML
    void salirGestionarComentariosAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/marketplaceView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private void cargarListaSolicitudes() {
        tblSolicitudesAmistad_gestionarAliados.getItems().clear();
        tblSolicitudesAmistad_gestionarAliados.setItems(obtenerSolicitudes());
    }
    //Kevin Agrego
    private ObservableList<Solicitud> obtenerSolicitudes() {
        listaSolicitudes.addAll(vendedor.getListaSolicitudes());
        return listaSolicitudes;
    }
    @FXML
    public void btnRechazarAction(ActionEvent actionEvent) throws DatosNulosException, VendedorException, VendedorNoExisteException, IOException {
        eliminarSolicitud();
        cargarListaSolicitudes();
    }
    private void eliminarSolicitud() throws VendedorNoExisteException, IOException, VendedorException {

        if (solicitudSelecionada != null) {

            if (mostrarMensajeConfirmacion("¿ Esta seguro de eliminar a esta Solicitud ?")) {

                vendedor = modelFactoryController.getVendedorAutenticado();
                modelFactoryController.eliminarSolicitud(solicitudSelecionada.getCedula());

                modelFactoryController.mostrarMensaje("Notificacion Solicitud", "Solicitud eliminada", "La solicitud Fue eliminada exitosamente",
                        Alert.AlertType.INFORMATION);
                modelFactoryController.registrarAccionesSistema("El vendedor no fue eliminado", 1, "Vendedor eliminado");
                cargarListaSolicitudes();
                tblSolicitudesAmistad_gestionarAliados.refresh();
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

    /*
    public void eliminarSolicitud() throws DatosNulosException {

        if (solicitudSelecionada != null) {

            modelFactoryController.eliminarSolicitud( solicitudSelecionada.getNombre(), vendedor.getCedula());
            cargarListaSolicitudes();
            modelFactoryController.mostrarMensaje("Notificacion Solicitud", "Solicitud Borrada Exitosamente", "Solicitud borrada", Alert.AlertType.CONFIRMATION);

            try {
                throw new DatosNulosException("Datos nulos al agregar producto");

            }catch (Exception e){
                modelFactoryController.registrarAccionesSistema(e.toString(), 2, "Datos nulos");
                }
            }
            modelFactoryController.mostrarMensaje("Notificacion Solicitud", "Producto no registrado", "No se selecciono algun producto o valores erroneos",
                    Alert.AlertType.WARNING);
    }
     */

    public void recibirNombre(String lbNombre1) throws VendedorNoExisteException {
        vendedor = modelFactoryController.loginVendedor(lbNombre1);
        cargarListaSolicitudes();
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
}



