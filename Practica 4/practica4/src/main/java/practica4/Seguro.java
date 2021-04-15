package practica4;

import java.util.Date;

public class Seguro {
	//Atributos privados
	private Date fechaUltimoSiniestro;
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
		//TODO
		return 0.0;
	}
	
	//Getters
	/**
	 * Metodo que retorna la fecha del ultimo siniestro.
	 * @return fechaUltimoSiniestro
	 */
	public Date fechaUltimoSiniestro() {
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
}
