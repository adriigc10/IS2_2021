package es.unican.is2.practica5modificada;

import java.util.LinkedList;
import java.util.List;

// WMC = 5
// CCOGtotal = 1
// CBO = 2 
// NOC = 0
// DIT = 0
public class Cliente {
	
	public String nombre;
	public String telefono;
	public String dni;
	public Direccion direccion; //CBO += 1 (Clase Direccion)
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>(); //CBO += 1 (Clase Cuenta)

 	public Cliente(String titular, String calle, String codigoPostal, String localidad,
 			String telefono, String dni) {  // CC += 1
		this.nombre = titular;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = new Direccion(calle, codigoPostal, localidad);
	}
	
	public void cambiarDireccion(Direccion direccion) { // CC += 1
		this.direccion = direccion;
	}
	
	public double getSaldoTotal() { // CC += 1
		double total = 0.0;
		for (Cuenta c: Cuentas) {   // CC += 1, CCOG += 1
			total +=  c.getSaldo();
		}
		return total;
	}
	
	public void anhadeCuenta(Cuenta c) { // CC += 1
		Cuentas.add(c);
	}
	
}