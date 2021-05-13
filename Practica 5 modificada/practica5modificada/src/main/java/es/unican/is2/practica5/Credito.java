package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {
		super(numero, titular, c);
		this.credito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importeRetirar Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double importeRetirar) throws saldoInsuficienteException, datoErroneoException {
		if (importeRetirar<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Retirada en cajero automático");
		importeRetirar += importeRetirar * 0.05; // Añadimos una comisión de un 5%
		movimiento.setImporte(-importeRetirar);
		
		if (getGastosAcumulados()+importeRetirar > credito)
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			movimientosMensuales.add(movimiento);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double importeAPagar) throws saldoInsuficienteException, datoErroneoException {
		if (importeAPagar<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + importeAPagar > credito)
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Compra a crédito en: " + datos);
		movimiento.setImporte(-importeAPagar);
		movimientosMensuales.add(movimiento);
	}
	
    public double getGastosAcumulados() {
		double gastosAcumulados = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) {
			Movimiento movimiento = (Movimiento) movimientosMensuales.get(i);
			gastosAcumulados += movimiento.getImporte();
		}
		return -gastosAcumulados;
	}
	
	
	public LocalDate getCaducidadCredito() {
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liquidar = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liquidar.setFecha(now);
		liquidar.setConcepto("Liquidación de operaciones tarjeta crédito"); //DESDE AQUI ES LO MISMO QUE getGastosAcumulados
		double gastosAcumulado = 0.0; 
		for (int i = 0; i < this.movimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			gastosAcumulado += m.getImporte();
		} //HASTA AQUI 
		liquidar.setImporte(gastosAcumulado);
	
		if (gastosAcumulado != 0)
			cuentaAsociada.addMovimiento(liquidar);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() {
		return movimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() {
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}

}