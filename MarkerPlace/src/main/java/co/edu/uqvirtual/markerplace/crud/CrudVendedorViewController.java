package co.edu.uqvirtual.markerplace.crud;

import co.edu.uqvirtual.markerplace.controllers.ModelFactoryController;
import co.edu.uqvirtual.markerplace.modelo.MarketPlace;
import co.edu.uqvirtual.markerplace.modelo.Vendedor;

import java.util.Optional;

public class CrudVendedorViewController {
    ModelFactoryController modelFactoryController;
    MarketPlace marketPlace;


    public CrudVendedorViewController(ModelFactoryController modelFactoryController) {
        this.modelFactoryController = modelFactoryController;
        marketPlace = modelFactoryController.getMarketPlace();
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
