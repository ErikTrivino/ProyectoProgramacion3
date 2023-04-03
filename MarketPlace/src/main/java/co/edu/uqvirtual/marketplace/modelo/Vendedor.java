package co.edu.uqvirtual.marketplace.modelo;

import java.util.ArrayList;

public class Vendedor extends Persona{
    private Usuario usuario;
    private Usuario contrasenia;
    private String direccion;
    private ArrayList<Vendedor> listaAliados;
    private ArrayList<Producto>listaProductos;
    private  ArrayList<Producto>listaProdutosSugeridos;
    private ArrayList<Chat>listaChat;
    private  ArrayList<Solicitud>listaSolicitudes;
    private  ArrayList<Solicitud>listaSolicitudesEnivadas;


    public Vendedor(String nombre, String apellido, String cedula, Usuario usuario) {
        super(nombre, apellido, cedula);
        listaAliados = new ArrayList<>();
        listaProductos = new ArrayList<>();
        listaProdutosSugeridos = new ArrayList<>();
        listaChat = new ArrayList<>();
        listaSolicitudes = new ArrayList<>();
        listaSolicitudesEnivadas = new ArrayList<>();
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Vendedor> getListaAliados() {
        return listaAliados;
    }

    public void setListaAliados(ArrayList<Vendedor> listaAliados) {
        this.listaAliados = listaAliados;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Producto> getListaProdutosSugeridos() {
        return listaProdutosSugeridos;
    }

    public void setListaProdutosSugeridos(ArrayList<Producto> listaProdutosSugeridos) {
        this.listaProdutosSugeridos = listaProdutosSugeridos;
    }

    public ArrayList<Chat> getListaChat() {
        return listaChat;
    }

    public void setListaChat(ArrayList<Chat> listaChat) {
        this.listaChat = listaChat;
    }

    public ArrayList<Solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(ArrayList<Solicitud> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public ArrayList<Solicitud> getListaSolicitudesEnivadas() {
        return listaSolicitudesEnivadas;
    }

    public void setListaSolicitudesEnivadas(ArrayList<Solicitud> listaSolicitudesEnivadas) {
        this.listaSolicitudesEnivadas = listaSolicitudesEnivadas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(Usuario contrasenia) {
        this.contrasenia = contrasenia;
    }

}
