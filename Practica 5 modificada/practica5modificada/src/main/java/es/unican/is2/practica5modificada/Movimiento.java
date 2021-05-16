package es.unican.is2.practica5modificada;

import java.time.LocalDateTime;

// WMC =  6
// CCOGtotal = 0
// CBO = 2 (Clases Credito, CuentaAhorro)
// DIT = 0
// NOC = 0

public class Movimiento { 
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getImporte() { // CC += 1
		return importe;
	}

	public String getConcepto() { // CC += 1
		return concepto;
	}

	public void setConcepto(String newConcepto) { // CC += 1
		concepto = newConcepto;
	}

	public LocalDateTime getFecha() { // CC += 1 
		return fecha;
	}

	public void setFecha(LocalDateTime newFecha) { // CC += 1
		fecha = newFecha;
	}

	public void setImporte(double newImporte) { // CC += 1
		importe = newImporte;
	}
}