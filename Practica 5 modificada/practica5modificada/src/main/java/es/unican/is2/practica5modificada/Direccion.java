package es.unican.is2.practica5modificada;

// WMC = 1
// CCOGtotal = 0
// CBO = 1 (Clase Cliente)
// DIT = 0
// NOC = 0

public class Direccion {

	protected String calle;
	protected String codigoPostal;
	protected String localidad;

	public Direccion(String calle, String codigoPostal, String localidad) { // CC += 1
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
	}	
}
