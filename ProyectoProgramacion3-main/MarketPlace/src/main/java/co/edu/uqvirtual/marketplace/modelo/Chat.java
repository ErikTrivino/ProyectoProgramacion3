package co.edu.uqvirtual.marketplace.modelo;

public class Chat {
    private String mensaje;
    String VendedorAsociado;


    public Chat() {
    }

    public Chat(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
