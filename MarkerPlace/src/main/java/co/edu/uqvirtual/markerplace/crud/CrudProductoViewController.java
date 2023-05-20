package co.edu.uqvirtual.markerplace.crud;

import co.edu.uqvirtual.markerplace.controllers.ModelFactoryController;
import co.edu.uqvirtual.markerplace.modelo.MarketPlace;
import co.edu.uqvirtual.markerplace.modelo.Producto;

import java.util.ArrayList;

public class CrudProductoViewController {
    ModelFactoryController modelFactoryController;
    MarketPlace marketPlace;


    public CrudProductoViewController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
        marketPlace = modelFactoryController.getMarketPlace();
    }
    public ArrayList<Producto> obtenerProductos(String cedula){
        return marketPlace.obtenerProductos(cedula);
    }


    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }
}
