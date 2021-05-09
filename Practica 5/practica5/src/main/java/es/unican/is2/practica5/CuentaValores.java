package es.unican.is2.practica5;

import java.util.List;

// WMC = 3
// CCOGtotal = 0
public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta, List<Valor> valores) { // CC += 1
		super(numCuenta);
		this.valores = valores;
	}
	
	public List<Valor> getValores() { // CC += 1
		return valores;
	}
	
	public void anhadeValor(Valor v) { // CC += 1
		valores.add(v);
	}
	
	
}
