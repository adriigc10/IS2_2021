package es.unican.is2.practica5modificada;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
// WMC = 13
// CCOGtotal = 5
// CBO = 7 (Clases Credito, Debito, Tarjeta)
// DIT = 1
// NOC = 0

public class CuentaAhorro extends Cuenta { // CBO += 1 (Clase Cuenta)

	private List<Movimiento> movimientos; // CBO += 1 (Clase Movimientos)

	public CuentaAhorro(String numCuenta) {  // CC += 1
		super(numCuenta);
		movimientos = new LinkedList<Movimiento>();
	}

	public void ingresar (String concepto, double importeIngresar){ // CC += 1
		comprobarImporteNegativo(importeIngresar);
		if (concepto == null) { // CC += 1, CCOG += 1
			concepto = "Ingreso en efectivo";
		}
		anhadirMovimiento(concepto, importeIngresar);
	}

	

	public void retirar(String concepto, double importeRetirar) throws SaldoInsuficienteException { // CC += 1, CBO += 1 (Clase saldoInsuficienteException)
		comprobarImporteNegativo(importeRetirar);
		if (getSaldo() < importeRetirar) // CC += 1, CCOG += 1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		if (concepto == null) { // CC += 1, CCOG += 1
			concepto = "Retirada de efectivo";
		}
		anhadirMovimiento(concepto, -importeRetirar);
	}


	public double getSaldo() { // CC += 1
		double gasto = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) { // CC += 1, CCOG += 1
			Movimiento m = movimientos.get(i);
			gasto += m.getImporte();
		}
		return gasto;
	}

	public void addMovimiento(Movimiento m) { // CC += 1
		movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { // CC += 1
		return movimientos;
	}
	
	private void comprobarImporteNegativo(double importeIngresar)  throws DatoErroneoException {// CC += 1, CBO += 1 (Clase datoErroneoException)
		if (importeIngresar <= 0) // CC += 1, CCOG += 1
			throw new DatoErroneoException("No se puede ingresar una cantidad negativa");
	}
	
	private void anhadirMovimiento(String concepto, double importeRetirar) { // CC += 1
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(importeRetirar);
		this.movimientos.add(movimiento);
	}

}