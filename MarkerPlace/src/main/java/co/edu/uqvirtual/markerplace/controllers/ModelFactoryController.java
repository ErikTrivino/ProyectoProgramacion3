package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorException;
import co.edu.uqvirtual.markerplace.modelo.MarketPlace;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController {
    MarketPlace marketPlace;
    String mensajelog = "";
    String nombreVendedorLog="";

    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }
    public ModelFactoryController() {
        inicializarDatos();
    }
    public void inicializarDatos() {

        marketPlace = new MarketPlace();


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

    public void eliminarVendedor(String cedula) throws VendedorException {



           this.getMarketPlace().eliminarVendedor(cedula);



    }

    public Vendedor obtenerVendedor(String cedula) {
        return null;
    }

    public ArrayList<Vendedor> obtenerEmpleados() {
        return this.getMarketPlace().obtenerEmpleados("");
    }

    public void actualizarVendedor(String nombre, String apellido, String cedula) throws VendedorException {

        this.getMarketPlace().actualizarVendedor(nombre, apellido, cedula);

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
    public Vendedor agregarVendedor(String nombre,String apellido,String cedula,String usuario,String contrasenia)throws DatosNulosException, IOException {
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
