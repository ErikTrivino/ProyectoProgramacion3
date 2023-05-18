package co.edu.uqvirtual.markerplace.modelo;

import java.io.Serial;
import java.io.Serializable;

public class Transaccion implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private String numeroTransaccion;
	private String fecha;

	private String nombreProducto;
	private Double valor;
	private String nombreVendedor;
	private String cedulaVendedor;
	private String nombreVendedorVenta;


	public Transaccion(String numeroTransaccion, String fecha, String nombreProducto, Double valor, String nombreVendedor, String cedulaVendedor, String nombreVendedorVenta) {
		this.numeroTransaccion = numeroTransaccion;
		this.fecha = fecha;
		this.nombreProducto = nombreProducto;
		this.valor = valor;
		this.nombreVendedor = nombreVendedor;
		this.cedulaVendedor = cedulaVendedor;
		this.nombreVendedorVenta = nombreVendedorVenta;
	}

	public Transaccion() {}


	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getCedulaVendedor() {
		return cedulaVendedor;
	}

	public void setCedulaVendedor(String cedulaVendedor) {
		this.cedulaVendedor = cedulaVendedor;
	}

	public String getNombreVendedorVenta() {
		return nombreVendedorVenta;
	}

	public void setNombreVendedorVenta(String nombreVendedorVenta) {
		this.nombreVendedorVenta = nombreVendedorVenta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
