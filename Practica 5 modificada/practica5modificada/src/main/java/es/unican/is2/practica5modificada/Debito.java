package es.unican.is2.practica5modificada;

import java.time.LocalDate;

// WMC = 6
// CCOGtotal = 1
// CBO = 3
// DIT = 1
// NOC = 0
public class Debito extends Tarjeta { // CBO += 1 (Clase Tarjeta)
	
	private double saldoDiarioDisponible;
	private final double limiteDebito = 1000;
	
	
	public Debito(String numero, String titular, CuentaAhorro c, LocalDate date) { // CC += 1, CBO += 1 (Clase CuentaAhorro)
		super(numero, titular, c, date);
	}
	
	
	@Override
	public void retirar(double importeRetirar) { // CC += 1
		comprobarSaldoDiarioDisponible(importeRetirar);
		this.cuentaAsociada.retirar("Retirada en cajero automático", importeRetirar);
		saldoDiarioDisponible-=importeRetirar;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double importeAPagar) { // CC += 1
		comprobarSaldoDiarioDisponible(importeAPagar);
		this.cuentaAsociada.retirar("Compra en : " + datos, importeAPagar);
		saldoDiarioDisponible-=importeAPagar;
	}

	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // CC += 1
		saldoDiarioDisponible = limiteDebito;
	}

	private void comprobarSaldoDiarioDisponible(double importeAPagar) throws SaldoInsuficienteException{ // CC += 1, CBO += 1 (Clase saldoInsuficienteException)
		if (saldoDiarioDisponible<importeAPagar) { // CC += 1, CCOG += 1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
	}

}