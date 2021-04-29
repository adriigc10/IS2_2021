package practica4;

import java.time.LocalDate;

public class Seguro {
	//Atributos privados
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	private Cliente tomadorSeguro;
	private Cobertura cobertura;
	
	//Constructor
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) {
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}
	
	/**
	 * Metodo que retorna el precio del seguro.
	 * @return precio del seguro
	 */
	public double precio() {
		double precio = 0;
		double precioBase = 0;
		double porcentajeSubida = 0;
		double subidaSiniestro = 0;
		double descuento = 0;
		
		
		//Nivel de cobertura contratado
		if(cobertura==Cobertura.TODORIESGO) {
			precioBase = 1000;
			precio += precioBase;
		}
		else if (cobertura == Cobertura.TERCEROS) {
			precioBase = 400;
			precio += precioBase;
		}
		else {
			precioBase = 600;
			precio += precioBase;
		}
			
		
		System.out.println("Precio cobertura: " + precioBase);

		//Potencia coche
		if(potenciaCV >= 90 && potenciaCV <= 110) {
			porcentajeSubida = precio * 0.05;
			precio += porcentajeSubida;
		}
		else if(potenciaCV > 110) {
			porcentajeSubida = precio * 0.20;
			precio += porcentajeSubida;
		}
		
		System.out.println("Precio potencia: " + porcentajeSubida);
		
		//Nivel de siniestralidad
		if(fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(365))) {
			subidaSiniestro = 200;
			precio += subidaSiniestro;
		}
		else if (fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1)) && 
				fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3))) {
			subidaSiniestro = 50;
			precio += subidaSiniestro;
		}
		
		System.out.println("Precio siniestro: " + subidaSiniestro);

		//Grado de minusvalia del tomador del seguro
		if(tomadorSeguro.minusvalia()) {
			descuento = precio * 0.25;
			precio -= descuento;
		}
		
		System.out.println("Precio minusvalia: " + descuento);
		
		System.out.println("Precio total: " + precio);

		return precio;
	}
	
	//Getters
	/**
	 * Metodo que retorna la fecha del ultimo siniestro.
	 * @return fechaUltimoSiniestro
	 */
	public LocalDate fechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}
	
	/**
	 * Metodo que retorna la potencia del vehiculo.
	 * @return potenciaCV
	 */
	public int potencia() {
		return potenciaCV;
	}
	
	/**
	 * Metodo que retorna el cliente a cargo del seguro.
	 * @return tomadorSeguro
	 */
	public Cliente tomadorSeguro() {
		return tomadorSeguro;
	}
	
	/**
	 * Metodo que devuleve el tipo de cobertura del seguro.
	 * @return cobertura
	 */
	public Cobertura cobertura() {
		return cobertura;
	}
	
	//Setters
	/**
	 * Metodo que establece la fecha del ultimo siniestro.
	 * @param fechaSiniestro
	 */
	public void setFechaUltimoSiniestro(LocalDate fechaSiniestro) {
		fechaUltimoSiniestro = fechaSiniestro;
	}
}
