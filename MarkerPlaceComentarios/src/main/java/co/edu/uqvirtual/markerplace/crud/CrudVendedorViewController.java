package co.edu.uqvirtual.markerplace.crud;

import co.edu.uqvirtual.markerplace.controllers.ModelFactoryController;
import co.edu.uqvirtual.markerplace.exceptions.VendedorNoExisteException;
import co.edu.uqvirtual.markerplace.modelo.MarketPlace;
import co.edu.uqvirtual.markerplace.modelo.Producto;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;

import java.util.Optional;

public class CrudVendedorViewController {
    ModelFactoryController modelFactoryController;
    MarketPlace marketPlace;


    public CrudVendedorViewController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
        marketPlace = modelFactoryController.getMarketPlace();
    }
    public boolean verificarVendedorExistente(String usuario) throws VendedorNoExisteException {
        return modelFactoryController.getMarketPlace().verificarVendedorExistente(usuario);
    }
    public boolean verficarLogin(String usuario, String contrasenia){
        return modelFactoryController.getMarketPlace().verificarLogin(usuario, contrasenia);

    }
    public Vendedor obtenerVendedor(String usuario) throws VendedorNoExisteException {
        return modelFactoryController.getMarketPlace().obtenerVendedor(usuario);

    }
    public void asignarVendedorActualModelFactory(Vendedor vendedor){
        modelFactoryController.setVendedorActual(vendedor);
    }
    public void asignarProductoSeleccionadoModelFactory(Producto producto){
        modelFactoryController.setProductoActual1(producto);
    }
    public Vendedor traerVendedorActalModelFactory(){
        return modelFactoryController.getVendedorActual();
    }


    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }
    public Optional<Vendedor> buscarVendedorLog(){
        return modelFactoryController.buscarVendedorLog();
    }
    public int obtenerPosicionVendedorLog(){
        return  modelFactoryController.obtenerPosicionLog();
    }

}
