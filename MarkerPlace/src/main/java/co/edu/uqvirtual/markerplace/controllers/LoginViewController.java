package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.exceptions.AccesoNoAutorizadoException;
import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginViewController {
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    public void setModelFactoryController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
    }

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContrasenia;



    @FXML
    private Button btnCancelar;

    @FXML
    void btnCancelar(ActionEvent event) {
        salir();
    }

    public void salir() {

        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }
    @FXML
    void btnIngresar(ActionEvent actionEvent) throws DatosNulosException, VendedorNoExisteException, IOException, AccesoNoAutorizadoException {
        String usuario=txtUsuario.getText();
        String contrasenia=txtContrasenia.getText();
        Vendedor vendedor=null;



        if (!(usuario.equals("") && contrasenia.equals(""))){

            if(modelFactoryController.marketPlace.obtenerAdmin(usuario, contrasenia)){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/co/edu/uqvirtual/markerplace/agregar-vendedor.fxml"));

                Parent root = loader.load();
                Scene scene = new Scene(root);
                //AdministradorViewController inicioForm = loader.getController();
                //inicioForm.init(user,cargoUsuario(user, pass));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                //stage.setTitle("Tu Finca Raíz | Inicio");
                stage.setScene(scene);
                salir();


            }else{
                if(modelFactoryController.verificarVendedorExistente(usuario)){
                    Vendedor v1 = modelFactoryController.obtenerVendedor(usuario);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ventanaAdmin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    //           VendedorView inicioForm = loader.getController();
                    //inicioForm.init(user,cargoUsuario(user, pass));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    //stage.setTitle("Tu Finca Raíz | Inicio");
                    stage.setScene(scene);

                }else{
                    mostrarMensaje("Datos incorrectos", null, "Asegurese de introducir todos los datos",
                            Alert.AlertType.ERROR );
                    try {
                        throw new AccesoNoAutorizadoException("El acceso no fue autorizado");
                    }catch (AccesoNoAutorizadoException e){
                        e.printStackTrace();
                    }

                }

            }


        }
        else {
            mostrarMensaje("Datos de acceso incompletos", null, "Asegurese de introducir todos los datos",
                    Alert.AlertType.ERROR);
            try {
                throw new AccesoNoAutorizadoException("El acceso no fue autorizado");
            }catch (AccesoNoAutorizadoException e){
                e.printStackTrace();
            }

        }


    }


    private void mostrarMensaje(String titulo, String head, String content, Alert.AlertType tipo) {
        Alert alerta = new Alert(null);
        alerta.setTitle(titulo);
        alerta.setHeaderText(head);
        alerta.setContentText(content);
        alerta.setAlertType(tipo);
        alerta.show();
    }








}
