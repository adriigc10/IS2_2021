package es.unican.is2.practica5;

public abstract class Tarjeta {
	protected String numero, titular;		
	protected CuentaAhorro cuentaAsociada;

	public Tarjeta(String numero, String titular, CuentaAhorro c) {
		this.numero = numero;
		this.titular = titular;
		cuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importeRetirar Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double importeRetirar) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param importeAPagar Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double importeAPagar)
			throws saldoInsuficienteException, datoErroneoException;
	
}