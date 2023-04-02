package co.edu.uqvirtual.marketplace.modelo;

public class Administrador extends Persona{
    private Usuario usuario;

    public Administrador(String nombre, String apellido, Integer edad, String cedula, Usuario usuario) {
        super(nombre, apellido, edad, cedula);
        this.usuario = usuario;
    }
}
