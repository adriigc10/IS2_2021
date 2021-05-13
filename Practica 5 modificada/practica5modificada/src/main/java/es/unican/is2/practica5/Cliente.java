package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String codigoPostal;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String codigoPostal, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String codigoPostal, String localidad) {
		this.calle = calle;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
	}
	
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {  
			if (c instanceof CuentaAhorro) {
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {
				for (Valor v: ((CuentaValores) c).getValores()) {
					total += v.getCotizacionActual()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
}