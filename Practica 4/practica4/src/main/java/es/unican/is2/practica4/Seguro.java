package es.unican.is2.practica4;

import java.time.LocalDate;

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

	//Constructor
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) {
		this.potenciaCV = potencia;
		this.tomadorSeguro = cliente;
		this.cobertura = cobertura;
	}

	public double precio() throws DatoIncorrectoException {

		double precio = 0.0;

		// gestion errores

		if (fechaUltimoSiniestro == null) { 
			System.out.println("Fecha nula");
			throw new DatoIncorrectoException();
		}

		if (fechaUltimoSiniestro.isAfter(LocalDate.now())) { 
			System.out.println("Fecha posterior a la actual");
			throw new DatoIncorrectoException();
		}

		if (potenciaCV <= 0) {
			System.out.println("Potencia negativa");
			throw new DatoIncorrectoException();
		}

		if (cobertura == null) {
			System.out.println("Cobertura incorrecta");
			throw new DatoIncorrectoException();
		}


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

		if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(366)) ) {
			precio += 200;
		} else if (fechaUltimoSiniestro.isAfter(LocalDate.now().minusDays(1096))) {
			precio += 50;
		}

		// minusvalia

		if (tomadorSeguro.minusvalia()) {
			precio = precio * DESCUENTO_MINUSVALIDOS;
		}

		//TODO: eliminar
		//System.out.println("Precio calculado: " + precio);

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

	public void setPotencia (int potencia) throws DatoIncorrectoException {
		potenciaCV = potencia;;
	}

	public void setCobertura (Cobertura cob) throws DatoIncorrectoException {
		cobertura = cob;;
	}
}