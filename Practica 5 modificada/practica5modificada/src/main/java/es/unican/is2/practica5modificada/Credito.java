package es.unican.is2.practica5modificada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

// WMC = 14
// CCOGtotal = 5
// CBO = 5
// DIT = 1
// NOC = 0
public class Credito extends Tarjeta { // CBO += 1 (Clase Tarjeta)
	
	private double creditoDisponible;
	private List<Movimiento> movimientosMensuales; // CBO += 1 (Clase Movimiento)
	private List<Movimiento> historicoMovimientos;
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito, LocalDate date) {  // CC += 1, CBO += 1 (Clase CuentaAhorro)
		super(numero, titular, c, date);
		this.creditoDisponible = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importeRetirar Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws SaldoInsuficienteException
	 * @throws DatoErroneoException
	 */
	@Override
	public void retirar(double importeRetirar) throws SaldoInsuficienteException, DatoErroneoException { // CC += 1, CBO += 2 (Class saldoInsuficienteException, datoErroneoException)
		comprobarImporteNegativo(importeRetirar);
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Retirada en cajero automático");
		importeRetirar += importeRetirar * 0.05; // Añadimos una comisión de un 5%
		movimiento.setImporte(-importeRetirar);
		
		if (getGastosAcumulados()+importeRetirar > creditoDisponible) // CC += 1, CCOG += 1
			throw new SaldoInsuficienteException("Crédito insuficiente");
		else {
			movimientosMensuales.add(movimiento);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double importeAPagar) throws SaldoInsuficienteException, DatoErroneoException {  // CC += 1
		comprobarImporteNegativo(importeAPagar);
		
		if (getGastosAcumulados() + importeAPagar > creditoDisponible) // CC += 1, CCOG += 1
			throw new SaldoInsuficienteException("Saldo insuficiente");
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Compra a crédito en: " + datos);
		movimiento.setImporte(-importeAPagar);
		movimientosMensuales.add(movimiento);
	}
	
    public double getGastosAcumulados() { // CC += 1
		return -calcularGastoAcumulado();
	}
	

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() { // CC += 1
		Movimiento liquidar = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liquidar.setFecha(now);
		liquidar.setConcepto("Liquidación de operaciones tarjeta crédito");
		
		double gastosAcumulado = calcularGastoAcumulado(); 
		
		liquidar.setImporte(gastosAcumulado);
	
		if (gastosAcumulado != 0) // CC += 1, CCOG += 1
			cuentaAsociada.addMovimiento(liquidar);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() { // CC += 1
		return movimientosMensuales;
	}
	
	public List<Movimiento> getMovimientos() { // CC += 1
		return historicoMovimientos;
	}

	
	private void comprobarImporteNegativo(double importeRetirar) { // CC += 1
		if (importeRetirar<0) // CC += 1, CCOG += 1
			throw new DatoErroneoException("No se puede retirar una cantidad negativa");
	}
	
	/**
	 * Metodo que decuelve el gasto acumulado de todas las cuentas de un cliente.
	 * @return gastos acumulados de un cliente
	 */
	private double calcularGastoAcumulado() { // CC += 1
		double gastosAcumulado = 0.0; 
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { // CC += 1, CCOG += 1
			Movimiento m = movimientosMensuales.get(i);
			gastosAcumulado += m.getImporte();
		}
		return gastosAcumulado;
	}


}