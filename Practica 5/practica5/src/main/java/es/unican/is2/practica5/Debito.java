package es.unican.is2.practica5;

import java.time.LocalDate;

// WMC = 8
// CCOGtotal = 2
// CBO = 4
// DIT = 1
// NOC = 0

public class Debito extends Tarjeta { // CBO += 1 (Clase Tarjeta)
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) { // CC += 1, CBO += 1 (Clase CuentaAhorro)
		super(numero, titular, c);
	}
	
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CC += 1, CBO += 2 (Clase saldoInsuficienteException, datoErroneoException)
		if (saldoDiarioDisponible<x) { // CC += 1, CCOG += 1 
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CC += 1
		if (saldoDiarioDisponible<x) { // CC += 1, CCOG += 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() { // CC += 1
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { // CC += 1
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() { // CC += 1
		return mCuentaAsociada;
	}

}