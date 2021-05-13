package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> movimientos;
	private LocalDate fechaCaducidadTarjetaDebito;
	private LocalDate fechaCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) {
		super(numCuenta);
		this.fechaCaducidadTarjetaDebito = date;
		this.fechaCaducidadTarjetaCredito = date2;
		movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double importeIngresar) throws datoErroneoException {
		if (importeIngresar <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Ingreso en efectivo");
		movimiento.setImporte(importeIngresar);
		this.movimientos.add(movimiento);
	}

	public void retirar(double importeRetirar) throws saldoInsuficienteException, datoErroneoException {
		if (importeRetirar <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < importeRetirar)
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto("Retirada de efectivo");
		movimiento.setImporte(-importeRetirar);
		this.movimientos.add(movimiento);
	}

	public void ingresar(String concepto, double importeIngresar) throws datoErroneoException {
		if (importeIngresar <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(importeIngresar);
		this.movimientos.add(movimiento);
	}

	public void retirar(String concepto, double importeRetirar) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < importeRetirar)
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (importeRetirar <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento movimiento = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		movimiento.setFecha(now);
		movimiento.setConcepto(concepto);
		movimiento.setImporte(-importeRetirar);
		this.movimientos.add(movimiento);
	}

	//TODO Revisar que es igual al metodo getGastoAcumulado de la clase Credito
	public double getSaldo() {
		double gasto = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) {
			Movimiento m = (Movimiento) movimientos.get(i);
			gasto += m.getImporte();
		}
		return gasto;
	}

	public void addMovimiento(Movimiento m) {
		movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public LocalDate getCaducidadDebito() {
		return this.fechaCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() {
		return this.fechaCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() {
		return limiteDebito;
	}

}