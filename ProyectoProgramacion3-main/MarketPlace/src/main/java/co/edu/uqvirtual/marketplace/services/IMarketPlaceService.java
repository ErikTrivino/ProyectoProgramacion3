package co.edu.uqvirtual.marketplace.services;

import co.edu.uqvirtual.marketplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.marketplace.exceptions.VendedorException;
import co.edu.uqvirtual.marketplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.marketplace.modelo.Vendedor;

import java.util.ArrayList;

public interface IMarketPlaceService {
    public Vendedor crearVendedor(String nombre, String apellido, Integer edad, String cedula, String usuario, String contrasenia) throws DatosNulosException;
    public boolean actualizarVendedor(String cedulaActual,String nombre, String apellido, String cedula) throws VendedorException;
    public Boolean eliminarVendedor(String cedula)throws VendedorException;
    public boolean  verificarVendedorExistente(String cedula) throws VendedorNoExisteException;;
    public Vendedor obtenerVendedor(String cedula) throws VendedorNoExisteException;
    public ArrayList<Vendedor> obtenerEmpleados(String cedula);
}
