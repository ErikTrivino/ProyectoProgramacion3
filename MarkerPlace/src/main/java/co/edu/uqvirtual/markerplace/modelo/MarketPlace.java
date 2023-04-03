package co.edu.uqvirtual.markerplace.modelo;

import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorException;
import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.services.IMarketPlaceService;


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
    public void actualizarVendedor(String nombre, String apellido, String cedula) throws VendedorException {


    int pos = obtenerPosicionVendedor(cedula);

    Vendedor v1 =  listaVendedores.get(pos);
    v1.setNombre(nombre);
    v1.setApellido(apellido);
    v1.setCedula(cedula);


    listaVendedores.set(pos, v1);

}
    public int obtenerPosicionVendedor(String cedula){
        int posicion = 0;
        boolean flagEncontrado = false;
        do {
            for(int i = 0; i < listaVendedores.size(); i++){
                if (listaVendedores.get(i).getCedula().equals(cedula)) {
                    posicion = i;
                    flagEncontrado = true;
                }
            }
            if(flagEncontrado == false){
                posicion = -1;

            }

        }while(flagEncontrado == false);
        return posicion;
    }

    @Override
    public void eliminarVendedor(String cedula) throws VendedorException {
        int pos = obtenerPosicionVendedor(cedula);
        listaVendedores.remove(pos);

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
