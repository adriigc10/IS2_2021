package es.unican.is2.practica5;

// WMC = 1
//CCOGtotal = 0
// CBO = 5 (Clases Credito, Debito)
// DIT = 0
// NOC = 2

public abstract class Tarjeta {
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada; // CBO += 1 (Clase CuentaAhorro)

	public Tarjeta(String numero, String titular, CuentaAhorro c) { // CC += 1
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException; // CBO += 1 (Clase saldoInsuficienteException, datoErroneoException)

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException;
	
}