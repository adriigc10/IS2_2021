package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getImporte() {
		return importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String newConcepto) {
		concepto = newConcepto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime newFecha) {
		fecha = newFecha;
	}

	public void setImporte(double newImporte) {
		importe = newImporte;
	}
}