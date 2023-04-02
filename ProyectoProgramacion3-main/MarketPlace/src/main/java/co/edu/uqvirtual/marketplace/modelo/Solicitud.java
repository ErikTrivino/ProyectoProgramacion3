package co.edu.uqvirtual.marketplace.modelo;

public class Solicitud {
    private String nombre;
    private String apellido;
    private boolean respuesta;

    public Solicitud(String nombre, String apellido, boolean respuesta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.respuesta = respuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }
}
