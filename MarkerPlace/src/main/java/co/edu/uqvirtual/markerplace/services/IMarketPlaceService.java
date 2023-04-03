package co.edu.uqvirtual.markerplace.services;

import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;


import java.util.ArrayList;

public interface IMarketPlaceService {
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia) throws DatosNulosException;
    public void actualizarVendedor(String nombre, String apellido, String cedula) throws VendedorException;
    public void eliminarVendedor(String cedula)throws VendedorException;
    public boolean  verificarVendedorExistente(String cedula) throws VendedorNoExisteException;;
    public Vendedor obtenerVendedor(String cedula) throws VendedorNoExisteException;
    public ArrayList<Vendedor> obtenerEmpleados(String cedula);
}
