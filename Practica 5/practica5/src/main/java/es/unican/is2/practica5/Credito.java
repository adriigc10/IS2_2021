package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// WMC = 16
// CCOGtotal = 7
// CBO = 6
// DIT = 1
// NOC = 0

public class Credito extends Tarjeta { // CBO += 1 (Clase Tarjeta)
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales; // CBO += 1 (Clase Movimiento)
	private List<Movimiento> mhistoricoMovimientos;
	

	public Credito(String numero, String titular, CuentaAhorro c, double credito) { // CC += 1, CBO += 1 (Clase CuentaAhorro)
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}


	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CC += 1, CBO += 2 (Class saldoInsuficienteException, datoErroneoException)
		if (x<0) // CC += 1, CCOG += 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero autom�tico");
		x += x * 0.05; // A�adimos una comisi�n de un 5%
		m.setI(-x);
		
		if (getGastosAcumulados()+x > mCredito) // CC += 1, CCOG += 1
			throw new saldoInsuficienteException("Cr�dito insuficiente");
		else { 
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { // CC += 1
		if (x<0) // CC += 1, CCOG += 1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) // CC += 1, CCOG += 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a cr�dito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() { // CC += 1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // CC += 1, CCOG += 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
	
	public LocalDate getCaducidadCredito() { // CC += 1
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	
	/**
	 * M�todo que se invoca autom�ticamente el d�a 1 de cada mes
	 */
	public void liquidar() { //CC += 1
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidaci�n de operaciones tarjeta cr�dito");
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { // CC += 1, CCOG += 1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		if (r != 0) // CC += 1, CCOG += 1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() { // CC += 1
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() { // CC += 1, CBO += 1 (Clase Cuenta)
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { // CC += 1
		return mhistoricoMovimientos;
	}

}