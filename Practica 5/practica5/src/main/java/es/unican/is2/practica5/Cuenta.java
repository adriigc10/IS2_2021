package es.unican.is2.practica5;

// WMC = 2
// CCOGtotal = 0
public abstract class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { // CC += 1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { // CC += 1
		return numCuenta;
	}
	
	
}
