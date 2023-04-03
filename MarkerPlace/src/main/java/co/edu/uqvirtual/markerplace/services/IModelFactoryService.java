package co.edu.uqvirtual.markerplace.services;

import co.edu.uqvirtual.markerplace.modelo.Vendedor;


import java.util.ArrayList;

public interface IModelFactoryService {
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String fechaNacimiento);
    public boolean actualizarVendedor(String cedulaActual,String nombre, String apellido, String cedula, String fechaNacimiento) ;
    public Boolean eliminarVendedor(String cedula);
    public boolean  verificarVendedorExistente(String cedula) ;
    public Vendedor obtenerVendedor(String cedula) ;
    public ArrayList<Vendedor> obtenerEmpleados(String cedula);
}
