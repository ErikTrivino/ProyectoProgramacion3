package co.edu.uqvirtual.marketplace.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginViewController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContrasenia;

    @FXML
    private Button btnIngresar;

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

}
