package co.edu.uqvirtual.marketplace.controllers;

import co.edu.uqvirtual.marketplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.marketplace.exceptions.VendedorException;
import co.edu.uqvirtual.marketplace.modelo.MarketPlace;
import co.edu.uqvirtual.marketplace.modelo.Vendedor;

import java.util.ArrayList;

public class ModelFactoryController {
    MarketPlace marketPlace;

    public MarketPlace getMarketPlace() {
        return this.marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }

    public Vendedor crearVendedor(String nombre, String apellido, Integer edad, String cedula, String usuario, String contrasenia) {
        Vendedor vendedor = null;

        try {
            vendedor = this.getMarketPlace().crearVendedor(nombre, apellido, edad, cedula, usuario, contrasenia);
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

}
