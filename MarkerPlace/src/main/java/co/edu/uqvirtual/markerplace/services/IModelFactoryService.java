package co.edu.uqvirtual.markerplace.services;

import co.edu.uqvirtual.markerplace.exceptions.VendedorException;
import co.edu.uqvirtual.markerplace.modelo.Estado;
import co.edu.uqvirtual.markerplace.modelo.Producto;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;


import java.util.ArrayList;

public interface IModelFactoryService {
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia);
    public void actualizarVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia)  ;
    public void eliminarVendedor(String cedula) ;
    public boolean  verificarVendedorExistente(String cedula) ;
    public Vendedor obtenerVendedor(String nombreUsuario) ;
    public ArrayList<Vendedor> obtenerVendedores();

    public Producto crearProducto(String nombre, String imagen, String precio, Estado estado,Vendedor vendedor);
    public void  actualizarProducto(String nombre, String imagen, String precio, Estado estado,Vendedor vendedor);
    public void eliminarProducto(String nombre,Vendedor vendedor);
    public Producto obtenerProducto(String nombre,Vendedor vendedor);
    public boolean verificarProductoExistente(String nombre,Vendedor vendedor);
    public ArrayList<Producto> obtenerProductos(Vendedor vendedor);
}
