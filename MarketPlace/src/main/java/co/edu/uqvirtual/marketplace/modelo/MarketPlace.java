package co.edu.uqvirtual.marketplace.modelo;

import co.edu.uqvirtual.marketplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.marketplace.exceptions.VendedorException;
import co.edu.uqvirtual.marketplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.marketplace.services.IMarketPlaceService;

import java.util.ArrayList;
import java.util.Optional;

public class MarketPlace implements IMarketPlaceService {
    private ArrayList<Vendedor> listaVendedores;
    private Administrador admin;

    public MarketPlace() {
        listaVendedores = new ArrayList<>();
        admin = new Administrador("Erik", "Triviño", "1234", new Usuario("admin", "1234"));
    }
    public boolean obtenerAdmin(String nombreUsuario, String contrsenia){
        if(nombreUsuario.equals(admin.getUsuario().getNombreUsuario()) && contrsenia.equals(admin.getUsuario().getContrasenia())){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public Vendedor crearVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia) throws  DatosNulosException {

        if (nombre == null && apellido == null && usuario == null && contrasenia == null) {
            throw new DatosNulosException("Los datos estan nulos");
        }
        Vendedor v1 = new Vendedor(nombre, apellido, cedula, new Usuario(usuario, contrasenia));

        listaVendedores.add(v1);

        return v1;
    }

    @Override
    public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula) throws VendedorException {
        return false;
    }

    @Override
    public Boolean eliminarVendedor(String cedula) throws VendedorException {
        return null;
    }

    @Override
    public boolean verificarVendedorExistente(String cedula) throws VendedorNoExisteException {


       Optional<Vendedor> v1 =  listaVendedores.stream().filter(x-> x.getCedula()==cedula).findFirst();
        if(v1.isPresent()){
            return true;
        }else{
            throw new VendedorNoExisteException("No existe elemento");

        }
    }

    @Override
    public Vendedor obtenerVendedor(String nombreUsuario) throws VendedorNoExisteException {
        return listaVendedores.stream().filter(x -> x.getUsuario().getNombreUsuario() == nombreUsuario).findFirst().get();
    }

    @Override
    public ArrayList<Vendedor> obtenerEmpleados(String cedula) {

        return listaVendedores;
    }
    public boolean existenciaVendedor(String nombreUsuario) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getUsuario().getNombreUsuario().equals(nombreUsuario)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vendedor> getListVendedores() {
        return listaVendedores;
    }
}
