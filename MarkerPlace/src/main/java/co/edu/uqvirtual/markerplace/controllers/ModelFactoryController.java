package co.edu.uqvirtual.markerplace.controllers;

import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorException;
import co.edu.uqvirtual.markerplace.modelo.Estado;
import co.edu.uqvirtual.markerplace.modelo.MarketPlace;
import co.edu.uqvirtual.markerplace.modelo.Producto;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;

import co.edu.uqvirtual.markerplace.services.IModelFactoryService;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController implements IModelFactoryService {
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



    /**
     *
     * CRUD VENDEDORES
     */
    @Override
    public void eliminarVendedor(String cedula)  {

        try {
            this.getMarketPlace().eliminarVendedor(cedula);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean verificarVendedorExistente(String cedula) {
        try {
            return marketPlace.verificarVendedorExistente(cedula);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public Vendedor obtenerVendedor(String nombreUsuario) {
        try {
            return getMarketPlace().obtenerVendedor(nombreUsuario);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia) {
        Vendedor vendedor = null;

        try {
            vendedor = marketPlace.crearVendedor(nombre, apellido, cedula, usuario, contrasenia);

        } catch (DatosNulosException e1) {
            e1.printStackTrace();
        }

        return vendedor;
    }

    @Override
    public void actualizarVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia)  {

        try{
            this.getMarketPlace().actualizarVendedor(nombre,apellido,cedula,usuario,contrasenia);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     *
     * CRUD PRODUCTOS
     */


    @Override
    public ArrayList<Vendedor> obtenerVendedores() {
        return marketPlace.obtenerEmpleados();
    }

    /**
     * CRUD PRODUCTOS
     * */

    @Override
    public Producto crearProducto(String nombre, String imagen, String precio, Estado estado,Vendedor vendedor) {
        try{
           Producto p1 =  this.getMarketPlace().crearProducto(nombre,imagen, precio, estado,vendedor);
            return p1;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public void actualizarProducto(String nombre, String imagen, String precio, Estado estado,Vendedor vendedor) {
        try{
            this.getMarketPlace().actualizarProducto(nombre, imagen, precio, estado, vendedor);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void eliminarProducto(String nombre,Vendedor vendedor) {

        try{
            this.getMarketPlace().eliminarProducto(nombre, vendedor);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtenerProducto(String nombre,Vendedor vendedor) {
        try{
            return this.getMarketPlace().obtenerProducto(nombre, vendedor);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean verificarProductoExistente(String nombre,Vendedor vendedor) {
        try{
            return this.getMarketPlace().verificarProductoExistente(nombre, vendedor);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ArrayList<Producto> obtenerProductos(Vendedor vendedor) {
        return this.getMarketPlace().obtenerProductos(vendedor);
    }





    void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert alerta = new Alert(alertType);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.setContentText(contenido);
        alerta.showAndWait();

    }




}
