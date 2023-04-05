package co.edu.uqvirtual.markerplace.modelo;

import co.edu.uqvirtual.markerplace.exceptions.DatosNulosException;
import co.edu.uqvirtual.markerplace.exceptions.ProductoNoExiste;
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

    public ArrayList<Vendedor> getListVendedores() {
        return listaVendedores;
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
    public void actualizarVendedor(String nombre, String apellido, String cedula, String usuario, String contrasenia) throws VendedorException {


    int pos = obtenerPosicionVendedor(cedula);

    Vendedor v1 =  listaVendedores.get(pos);
    v1.setNombre(nombre);
    v1.setApellido(apellido);



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
    public ArrayList<Vendedor> obtenerEmpleados() {

        return listaVendedores;
    }

    @Override
    public Producto crearProducto(String nombre, String imagen, String precio, Estado estado,Vendedor vendedor) throws DatosNulosException {
       Producto p1 = new Producto(nombre, imagen, precio, estado);
       int pos = obtenerPosicionVendedor(vendedor.getCedula());
       listaVendedores.get(pos).getListaProductos().add(p1);


        return  p1;
    }

    @Override
    public void actualizarProducto(String nombre, String imagen, String precio, Estado estado,Vendedor vendedor) throws DatosNulosException, ProductoNoExiste {
        int posVendedor = obtenerPosicionVendedor(vendedor.getCedula());
        Optional<Producto> p1 = listaVendedores.get(posVendedor).getListaProductos().stream().filter(x -> x.getNombre().equals(nombre)).findFirst();

        if(p1.isPresent()){

            int posPro = obtenerPosicionProducto(nombre, vendedor.getListaProductos());
            vendedor.getListaProductos().set(posPro, new Producto(nombre,imagen,  precio, estado));


        }else{
            throw new ProductoNoExiste("El producto no existe");
        }



    }
    public int obtenerPosicionProducto(String nombre, ArrayList<Producto> listaProductos){
        int posicion = 0;
        boolean flagEncontrado = false;
        do {
            for(int i = 0; i < listaProductos.size(); i++){
                if (listaProductos.get(i).getNombre().equals(nombre)) {
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
    public boolean verificarProductoExistente(String nombre,Vendedor vendedor) throws ProductoNoExiste {
        int posVendedor = obtenerPosicionVendedor(vendedor.getCedula());
        Optional<Producto> p1 = listaVendedores.get(posVendedor).getListaProductos().stream().filter(x -> x.getNombre().equals(nombre)).findFirst();

        if(p1.isPresent()){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void eliminarProducto(String nombre,Vendedor vendedor) throws ProductoNoExiste {

        int posVendedor = obtenerPosicionVendedor(vendedor.getCedula());
        int posProducto  = obtenerPosicionProducto(nombre, vendedor.getListaProductos());

        Optional<Producto> p1 = listaVendedores.get(posVendedor).getListaProductos().stream().filter(x -> x.getNombre().equals(nombre)).findFirst();

        if(p1.isPresent()){
            vendedor.getListaProductos().remove(posProducto);
        }else{
           throw new ProductoNoExiste("Prodcuto no existe");
        }




    }

    @Override
    public Producto obtenerProducto(String nombre,Vendedor vendedor) throws ProductoNoExiste {
        int posVendedor = obtenerPosicionVendedor(vendedor.getCedula());
        Optional<Producto> p1 = listaVendedores.get(posVendedor).getListaProductos().stream().filter(x -> x.getNombre().equals(nombre)).findFirst();

        if(p1.isPresent()){
            return p1.get();
        }else{
            throw new ProductoNoExiste("Producto no existe");
        }
    }

    @Override
    public ArrayList<Producto> obtenerProductos(Vendedor vendedor) {
        int posVendedor = obtenerPosicionVendedor(vendedor.getCedula());

        return listaVendedores.get(posVendedor).getListaProductos();
    }



}
