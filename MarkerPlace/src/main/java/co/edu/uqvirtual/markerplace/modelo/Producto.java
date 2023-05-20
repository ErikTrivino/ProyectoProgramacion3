package co.edu.uqvirtual.markerplace.modelo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String imagen;
    private String precio;
    private Estado estado;
    private ArrayList<Comentario> listaComentarioProductos;
    private ArrayList<MeGusta>listaMeGustaProducto;


    public Producto() {
    }

    public Producto(String nombre, String imagen, String precio, Estado estado) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.estado = estado;
        listaMeGustaProducto = new ArrayList<>();
        listaComentarioProductos = new ArrayList<>();
    }

    public ArrayList<Comentario> getListaComentarioProductos() {
        return listaComentarioProductos;
    }

    public void setListaComentarioProductos(ArrayList<Comentario> listaComentarioProductos) {
        this.listaComentarioProductos = listaComentarioProductos;
    }

    public ArrayList<MeGusta> getListaMeGustaProducto() {
        return listaMeGustaProducto;
    }

    public void setListaMeGustaProducto(ArrayList<MeGusta> listaMeGustaProducto) {
        this.listaMeGustaProducto = listaMeGustaProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
