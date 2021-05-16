package es.unican.is2.practica5modificada;

//WMC = 2
//CCOGtotal = 0
//CBO = 4 (Clases CuentaAhorro, CuentaValores, Cliente, Tarjeta)
//DIT = 0
//NOC = 2

public abstract class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { // CC += 1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() { // CC += 1
		return numCuenta;
	}
	
	public abstract double getSaldo();
}
