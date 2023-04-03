package co.edu.uqvirtual.marketplace.controllers;

import co.edu.uqvirtual.marketplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.marketplace.exceptions.VendedorException;
import co.edu.uqvirtual.marketplace.modelo.MarketPlace;
import co.edu.uqvirtual.marketplace.modelo.Vendedor;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController {
    MarketPlace marketPlace;
    String mensajelog = "";
    String nombreVendedorLog="";
    public static ModelFactoryController getInstance() {
        return null;
    }

    public MarketPlace getMarketPlace() {
        return this.marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }

    public Vendedor crearVendedor(String nombre, String apellido, Integer edad, String cedula, String usuario, String contrasenia) {
        Vendedor vendedor = null;

        try {
            vendedor = this.getMarketPlace().crearVendedor(nombre, apellido, cedula, usuario, contrasenia);
        } catch (DatosNulosException e1) {
            e1.printStackTrace();
        }

        return vendedor;
    }

    public Boolean eliminarVendedor(String cedula) {
        boolean flagExiste = false;

        try {
            flagExiste = this.getMarketPlace().eliminarVendedor(cedula);
        } catch (VendedorException e2) {
            e2.printStackTrace();
        }

        return flagExiste;
    }

    public Vendedor obtenerVendedor(String cedula) {
        return null;
    }

    public ArrayList<Vendedor> obtenerEmpleados() {
        return this.getMarketPlace().obtenerEmpleados("");
    }

    public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula) {
        boolean flagExiste = false;

        try {
            flagExiste = this.getMarketPlace().actualizarVendedor(cedulaActual, nombre, apellido, cedula);
        } catch (VendedorException e3) {
            e3.printStackTrace();
        }

        return flagExiste;
    }
    void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alerta = new Alert(alertType);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        alerta.showAndWait();

    }
    public boolean existenciaVendedor(String cedula) {
        return marketPlace.existenciaVendedor(cedula);
    }
    public ArrayList<Vendedor> obtenerVendedores() {
        return marketPlace.getListVendedores();
    }
    public Vendedor agregarVendedor(String nombre,String apellido, String direccion,String cedula,String usuario,String contrasenia)throws DatosNulosException, IOException {
        mensajelog="";
        nombreVendedorLog="";

        Vendedor vendedor = marketPlace.crearVendedor(nombre, apellido, cedula, usuario, contrasenia);



        if (vendedor != null) {
            mensajelog += "se guardo el vendedor satisfactorimente";
            nombreVendedorLog=vendedor.getNombre();
        } else {
            mensajelog += "no se guardo el vendedor";
            nombreVendedorLog=nombre;
        }

        MarketPlace datosAEnviar=new MarketPlace();

        datosAEnviar=marketPlace;

        return vendedor;
    }

}
