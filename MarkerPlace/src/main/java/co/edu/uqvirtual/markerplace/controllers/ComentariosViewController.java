package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.modelo.Comentario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.jshell.spi.SPIResolutionException;
import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComentariosViewController {

    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    Comentario comentario;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button butComenmtar;
    @FXML
    private Button btnSalir;

    @FXML
    private TableColumn<Comentario, String> colNombre;
    @FXML
    private TableColumn<Comentario, String> colCedula;
    @FXML
    private TableColumn<Comentario, String> colComentario;
    @FXML
    private TableView<Comentario> tblComentarios;

    @FXML
    private TextField texComentarios;

    @FXML
    void butComenmtar(ActionEvent event) {

    }

    ObservableList<Comentario> listadoComentarios = FXCollections.observableArrayList();



    @FXML
    void initialize() throws IOException {

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        this.colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));

        tblComentarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {


       });
        //cargarListadoProductos();

    }


    @FXML
    public void tbSalirAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/marketplaceView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
