module co.edu.uqvirtual.marketplace {
    requires javafx.controls;
    requires javafx.fxml;


    exports co.edu.uqvirtual.marketplace.aplicacion;
    opens co.edu.uqvirtual.marketplace.aplicacion to javafx.fxml;
    opens co.edu.uqvirtual.marketplace.controller to javafx.fxml;
}