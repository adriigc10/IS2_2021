package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) {
		super(numero, titular, c);
	}
	
	
	@Override
	public void retirar(double importeRetirar) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<importeRetirar) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero automático", importeRetirar);
		saldoDiarioDisponible-=importeRetirar;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double importeAPagar) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<importeAPagar) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, importeAPagar);
		saldoDiarioDisponible-=importeAPagar;
	}
	
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

}