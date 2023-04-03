package co.edu.uqvirtual.marketplace.modelo;

public class Administrador extends Persona{
    private Usuario usuario;

    public Administrador(String nombre, String apellido, String cedula, Usuario usuario) {
        super(nombre, apellido, cedula);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
