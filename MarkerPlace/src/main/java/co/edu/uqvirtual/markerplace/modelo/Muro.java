package co.edu.uqvirtual.markerplace.modelo;

import java.util.List;

public class Muro {

    private List<MeGusta> listaMegusta;
    private List<Vendedor> listaVendedor;

    public Muro() {

    }


    public List<MeGusta> getListaMegusta() {
        return listaMegusta;
    }

    public void setListaMegusta(List<MeGusta> listaMegusta) {
        this.listaMegusta = listaMegusta;
    }

    public List<Vendedor> getListaVendedor() {
        return listaVendedor;
    }

    public void setListaVendedor(List<Vendedor> listaVendedor) {
        this.listaVendedor = listaVendedor;
    }
}
