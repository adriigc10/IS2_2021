package es.unican.is2.practica5modificada;

import java.time.LocalDate;

// WMC = 3
// CCOGtotal = 0
// CBO = 5 (Clases Credito, Debito)
// DIT = 0
// NOC = 2

public abstract class Tarjeta {
	protected String numero, titular;		
	protected CuentaAhorro cuentaAsociada; // CBO += 1 (Clase CuentaAhorro)
	protected LocalDate fechaCaducidadTarjeta;

	public Tarjeta(String numero, String titular, CuentaAhorro c, LocalDate fechaCaducidadTarjeta) { // CC += 1
		this.numero = numero;
		this.titular = titular;
		this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
		cuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importeRetirar Cantidad a retirar. 
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void retirar(double importeRetirar) throws SaldoInsuficienteException, DatoErroneoException; // CBO += 2 (Clase saldoInsuficienteException, datoErroneoException)

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param importeAPagar Cantidada a pagar
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double importeAPagar)
			throws SaldoInsuficienteException, DatoErroneoException;

	public Cuenta getCuentaAsociada() { // CC += 1
		return cuentaAsociada;
	}
	
	public LocalDate getFechaCaducidadTarjeta() { // CC += 1
		return fechaCaducidadTarjeta;
	}
}