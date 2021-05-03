package es.unican.is2.practica4test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.practica4.Cliente;
import es.unican.is2.practica4.Cobertura;
import es.unican.is2.practica4.DatoIncorrectoException;
import es.unican.is2.practica4.Seguro;

public class SeguroTest {

	private Cliente cliente = new Cliente("Borja", "0001", true);
	private Seguro seguro;

	@Before
	public void setUp() throws Exception {
		seguro = new Seguro(1, cliente, Cobertura.TERCEROS);
	}


	@Test
	public void constructorTest() {

		// casos validos

		try {
			assertTrue(seguro instanceof Seguro);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado excepcion");
		}

		Seguro seguro1 = new Seguro(17, cliente, Cobertura.TODORIESGO);
		try {
			cliente.setNoMinusvalido();
			assertTrue(seguro1 instanceof Seguro);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		Seguro seguro2 = new Seguro(1, cliente, Cobertura.TERCEROSLUNAS);
		try {
			assertTrue(seguro2 instanceof Seguro);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// casos no validos
		//TODO: revisar
		
		try {
			Seguro seguro3 = new Seguro(0, cliente, Cobertura.TERCEROS);
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		
		try {
			Seguro seguro4 = new Seguro(925, cliente, null);
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		try {
			Seguro seguro5 = new Seguro(1, null, Cobertura.TERCEROSLUNAS);
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}
	}

	@Test
	public void precioTest() {

		// casos validos

		try {
			seguro.setPotencia(1);
			cliente.setMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			assertTrue(seguro.precio() == 450);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		try {
			seguro.setPotencia(89);
			cliente.setNoMinusvalido();
			seguro.setCobertura(Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(364));
			assertTrue(seguro.precio() == 1200.00);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}


		try {
			seguro.setPotencia(90);
			cliente.setMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue(seguro.precio() == 622.5);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		try {
			seguro.setPotencia(110);
			cliente.setNoMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1094));
			assertTrue(seguro.precio() == 470.00);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		try {
			seguro.setPotencia(111);
			cliente.setMinusvalido();
			seguro.setCobertura(Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1096));
			assertTrue(seguro.precio() == 900.00);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// casos no validos

		// fecha posterior a hoy
		try {
			seguro.setPotencia(1);
			cliente.setMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusDays(1));
			seguro.precio();
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// fecha null
		try {
			seguro.setPotencia(89);
			cliente.setNoMinusvalido();
			seguro.setCobertura(Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(null);
			seguro.precio();
			fail("Deberia lanzarse excepcion por fecha nula");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia
		try {
			seguro.setPotencia(0);
			cliente.setNoMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			seguro.precio();
			fail("Deberia lanzarse excepcion por potencia negativa");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		//TODO: ¿caso de prueba con potencia negativa?

		// cobertura
		try {
			seguro.setPotencia(90);
			cliente.setMinusvalido();
			seguro.setCobertura(null);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1096));
			seguro.precio();
			fail("No se ha lanzado la excepcion por cobertura nula");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}


		//TODO
		// cliente (¿y minusvalia?)
		try {
			seguro.setPotencia(110);
			cliente = null;
			cliente.setMinusvalido();
			fail("Deberia lanzarse excepcion por cliente nulo");
		} catch (NullPointerException e) {
			// funcionamiento correcto
		}
	}

}
