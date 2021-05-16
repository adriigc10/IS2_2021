package es.unican.is2.practica5modificada;

import java.util.List;

//WMC = 5
//CCOGtotal = 1
//CBO = 2
//DIT = 1
//NOC = 0

public class CuentaValores extends Cuenta { // CBO += 1 (Clase Cuenta)

	private List<Valor> valores; // CBO += 1 (Clase Valor)
	
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
	
	public double getSaldo() { // CC += 1
		double total = 0;
		for (Valor v: valores) { // CC += 1, CCOG += 1
			total += v.getCotizacionActual()*v.getNumValores();
		}
		return total;
	}
	
}
