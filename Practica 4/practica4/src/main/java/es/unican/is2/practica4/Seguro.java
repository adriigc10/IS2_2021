package es.unican.is2.practica4;

import java.time.LocalDate;

/**
 * Clase Seguro.
 * @author Borja Cuevas y Adrian Garcia.
 *
 */
public class Seguro {
	//Atributos privados
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	private Cliente tomadorSeguro;
	private Cobertura cobertura;

	private static final double MAX_POTENCIACV_INTERVALO = 110;
	private static final double MIN_POTENCIACV_INTERVALO = 90;
	private static final double INCREMENTO_MEDIO = 1.05;
	private static final double INCREMENTO_ALTO = 1.2;
	private static final double DESCUENTO_MINUSVALIDOS = 0.75;

	/**
	 * constructor de la clase.
	 * @param potencia potencia del coche.
	 * @param cliente tomador del seguro.
	 * @param cobertura cobertura del seguro.
	 */
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) {

		// gestion errores

		if (potencia <= 0) {
			System.out.println("Potencia negativa");
			throw new DatoIncorrectoException();
		}

		if (cobertura == null) {
			System.out.println("Cobertura incorrecta");
			throw new DatoIncorrectoException();
		}

		if (cliente == null) {
			System.out.println("Cliente nulo");
			throw new DatoIncorrectoException();
		}

		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}


	/**
	 * calcula y retorna el precio del seguro.
	 * @return precio precio del seguro.
	 * @throws DatoIncorrectoException en caso de que algun dato introducido sea incorrecto.
	 */
	public double precio() throws DatoIncorrectoException {

		double precio = 0.0;

		// cobertura seguro

		switch (cobertura) {

		case TODORIESGO:
			precio = 1000;
			break;

		case TERCEROS:
			precio = 400;
			break;

		case TERCEROSLUNAS:
			precio = 600;
			break;
		}


		// potencia coche

		if (potenciaCV >= MIN_POTENCIACV_INTERVALO && potenciaCV <= MAX_POTENCIACV_INTERVALO) {
			precio = precio * INCREMENTO_MEDIO;
		} else if (potenciaCV > MAX_POTENCIACV_INTERVALO) {
			precio = precio * INCREMENTO_ALTO;
		}

		// nivel de siniestralidad

		if (fechaUltimoSiniestro != null) {
			if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(366)) ) {
				precio += 200;
			} else if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(1095))) {
				precio += 50;
			}
		}

		// minusvalia

		if (tomadorSeguro.minusvalia()) {
			precio = precio * DESCUENTO_MINUSVALIDOS;
		}

		return precio;
	}

	//Getters
	
	/**
	 * Retorna la potencia del vehiculo.
	 * @return potenciaCV potencia del vehiculo.
	 */
	public int potencia() {
		return potenciaCV;
	}

	/**
	 * Retorna el cliente a cargo del seguro.
	 * @return tomadorSeguro tomador del seguro.
	 */
	public Cliente tomadorSeguro() {
		return tomadorSeguro;
	}

	/**
	 * Retorna el tipo de cobertura del seguro.
	 * @return cobertura cobertura del seguro.
	 */
	public Cobertura cobertura() {
		return cobertura;
	}

	//Setters
	/**
	 * Rstablece la fecha del ultimo siniestro.
	 * @param fechaSiniestro fecha del ultimo siniestro.
	 */
	public void setFechaUltimoSiniestro(LocalDate fechaSiniestro) {
		if (fechaSiniestro != null) {
			if (fechaSiniestro.isAfter(LocalDate.now())) { 
				System.out.println("Fecha posterior a la actual");
				throw new DatoIncorrectoException();
			}
		}
		fechaUltimoSiniestro = fechaSiniestro;
	}
}
